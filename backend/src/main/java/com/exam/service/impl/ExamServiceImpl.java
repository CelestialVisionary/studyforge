package com.exam.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.dto.SubmitAnswerDto;
import com.exam.dto.ai.ChatMessage;
import com.exam.entity.*;
import com.exam.mapper.AnswerRecordMapper;
import com.exam.mapper.ExamRecordMapper;
import com.exam.mapper.QuestionAnswerMapper;
import com.exam.mapper.QuestionMapper;
import com.exam.service.AIService;
import com.exam.service.ExamService;
import com.exam.service.PaperService;
import com.exam.service.KimiGradingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 考试服务实现类
 */
@Service
@Slf4j
public class ExamServiceImpl extends ServiceImpl<ExamRecordMapper, ExamRecord> implements ExamService {

    @Autowired
    private AnswerRecordMapper answerRecordMapper;
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;
    @Autowired
    private AIService aiService;
    @Autowired
    private KimiGradingService kimiGradingService;

    /**
     * 开始一场考试
     */
    @Override
    @Transactional
    public ExamRecord startExam(Integer paperId, String studentName, Integer userId) {
        // 1. 检查是否已存在未完成的考试记录
        QueryWrapper<ExamRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("exam_id", paperId)
               .eq("student_name", studentName)
               .eq("status", "进行中"); // 状态: 进行中
        ExamRecord existingRecord = this.getOne(wrapper);
        if (existingRecord != null) {
            return existingRecord; // 如果有，直接返回
        }

        // 2. 创建新的考试记录
        ExamRecord examRecord = new ExamRecord();
        examRecord.setExamId(Long.valueOf(paperId)); // 设置试卷ID
        examRecord.setStudentName(studentName); // 设置考生姓名
        examRecord.setStartTime(LocalDateTime.now());
        examRecord.setStatus("进行中"); // 进行中
        examRecord.setScore(0); // 初始分数为0
        this.save(examRecord);
        return examRecord;
    }

    /**
     * 提交答案
     */
    @Override
    @Transactional
    public void submitAnswers(Integer examRecordId, List<SubmitAnswerDto> answers) {
        // 1. 查找考试记录
        ExamRecord examRecord = this.getById(Long.valueOf(examRecordId));
        if (examRecord == null || !"进行中".equals(examRecord.getStatus())) {
            throw new RuntimeException("考试记录不存在或已完成");
        }

        // 2. 保存每一条答案
        answers.forEach(answerDto -> {
            AnswerRecord answerRecord = new AnswerRecord(examRecordId, answerDto.getQuestionId(), answerDto.getUserAnswer());
            answerRecordMapper.insert(answerRecord);
        });

        // 3. 更新考试记录状态为"已完成"
        examRecord.setEndTime(LocalDateTime.now());
        examRecord.setStatus("已完成"); // 已完成
        this.updateById(examRecord);
        
        // 4. 自动调用AI判卷
        log.info("试卷提交完成，开始自动AI智能判卷，考试记录ID: {}", examRecordId);
        try {
            this.gradeExam(examRecordId);
            log.info("自动AI判卷完成，考试记录ID: {}", examRecordId);
        } catch (Exception e) {
            log.error("自动AI判卷失败，考试记录ID: {}, 错误: {}", examRecordId, e.getMessage());
            // 判卷失败不影响提交，但记录错误
        }
    }

    /**
     * 批阅试卷（使用Kimi AI智能判卷）
     */
    @Override
    @Transactional
    public ExamRecord gradeExam(Integer examRecordId) {
        log.info("开始AI智能判卷，考试记录ID: {}", examRecordId);
        
        // 1. 获取考试记录和所有答案记录
        ExamRecord examRecord = this.getById(Long.valueOf(examRecordId));
        if (examRecord == null) {
            throw new RuntimeException("考试记录不存在");
        }
        
        if (!"已完成".equals(examRecord.getStatus())) {
            throw new RuntimeException("考试尚未完成，无法批阅");
        }
        
        List<AnswerRecord> answerRecords = answerRecordMapper.selectList(
            new QueryWrapper<AnswerRecord>().eq("exam_record_id", examRecordId));
        
        if (answerRecords.isEmpty()) {
            log.warn("考试记录ID: {} 没有找到答案记录", examRecordId);
            examRecord.setScore(0);
            examRecord.setStatus("已批阅");
            this.updateById(examRecord);
            return examRecord;
        }

        // 2. 获取试卷中的所有题目及其分值
        Paper paper = paperService.getPaperWithQuestions(examRecord.getExamId());
        if (paper == null || paper.getQuestions() == null) {
            throw new RuntimeException("试卷信息不完整");
        }
        
        Map<Long, Question> questionMap = new java.util.HashMap<>();
        paper.getQuestions().forEach(q -> questionMap.put(q.getId(), q));
        //Map<Long, Question> questionMapNew = paper.getQuestions().stream().collect(Collectors.toMap(Question::getId, q -> q));

        Integer totalScore = 0;
        Integer correctCount = 0;
        
        log.info("开始逐题判卷，共{}道题", answerRecords.size());

        // 3. 使用AI判卷（简答题）或直接判卷（客观题）
        for (AnswerRecord record : answerRecords) {
            Question question = questionMap.get(record.getQuestionId().longValue());
            if (question == null) {
                log.warn("题目ID: {} 在试卷中不存在，跳过", record.getQuestionId());
                continue;
            }

            // 获取题目满分
            Integer maxScore = question.getPaperScore() != null ? 
                question.getPaperScore().intValue() : 10;
            
            try {
                if ("CHOICE".equals(question.getType()) || "JUDGE".equals(question.getType())) {
                    // 客观题：直接对比标准答案
                    String userAnswer = record.getUserAnswer() != null ? record.getUserAnswer().trim() : "";
                    String standardAnswer = question.getAnswer() != null ? question.getAnswer().getAnswer().trim() : "";
                    
                    // 对于判断题，需要处理T/F与TRUE/FALSE的映射
                    if ("JUDGE".equals(question.getType())) {
                        // 统一转换为TRUE/FALSE格式进行比较
                        userAnswer = normalizeJudgeAnswer(userAnswer);
                        standardAnswer = normalizeJudgeAnswer(standardAnswer);
                    }
                    
                    if (userAnswer.equals(standardAnswer)) {
                        // 答案完全正确
                        record.setScore(maxScore);
                        record.setIsCorrect(1); // 完全正确
                        // 选择题和判断题不设置AI评语
                        record.setAiCorrection(null);
                        correctCount++;
                    } else {
                        // 答案错误
                        record.setScore(0);
                        record.setIsCorrect(0); // 错误
                        // 选择题和判断题不设置AI评语
                        record.setAiCorrection(null);
                    }
                    
                    log.info("客观题判卷完成，题目ID: {}, 用户答案: {}, 标准答案: {}, 得分: {}/{}",
                        question.getId(), record.getUserAnswer(), question.getAnswer().getAnswer(), record.getScore(), maxScore);
                        
                } else if ("TEXT".equals(question.getType())) {
                    // 主观题：使用Kimi AI进行智能判卷
                    KimiGradingService.GradingResult gradingResult = 
                        kimiGradingService.gradeQuestion(question, record.getUserAnswer(), maxScore);
                    
                    // 更新答案记录
                    record.setScore(gradingResult.getScore());
                    record.setAiCorrection(gradingResult.getFeedback());
                    
                    // 设置正确性标记
                    if (gradingResult.getScore().equals(maxScore)) {
                        record.setIsCorrect(1); // 完全正确
                        correctCount++;
                    } else if (gradingResult.getScore() > 0) {
                        record.setIsCorrect(2); // 部分正确
                    } else {
                        record.setIsCorrect(0); // 错误
                    }
                    
                    log.info("主观题AI判卷完成，题目ID: {}, 得分: {}/{}", 
                    question.getId(), gradingResult.getScore(), maxScore);
                }
                
                totalScore += record.getScore();
                
            } catch (Exception e) {
                log.error("判卷失败，题目ID: {}, 错误: {}", question.getId(), e.getMessage());
                // 判卷失败时给0分
                record.setScore(0);
                record.setIsCorrect(0);
                record.setAiCorrection("系统判卷失败，请联系管理员");
            }
            
            // 更新答案记录
            answerRecordMapper.updateById(record);
        }

        // 4. 生成考试总评，使用实际答题数量
        String examSummary = kimiGradingService.generateExamSummary(
            totalScore, paper.getTotalScore().intValue(), 
            answerRecords.size(), correctCount); // 使用实际答题记录数量而不是试卷配置数量
        
        // 5. 更新考试记录
        examRecord.setScore(totalScore);
        examRecord.setStatus("已批阅");
        examRecord.setAnswers(examSummary); // 将AI总评存储在answers字段中
        this.updateById(examRecord);
        
        log.info("AI智能判卷完成，考试记录ID: {}, 总分: {}/{}", 
            examRecordId, totalScore, paper.getTotalScore());

        return examRecord;
    }

    /**
     * 获取考试记录详情（包含试卷信息和答题记录）
     */
    @Override
    public ExamRecord getExamRecordDetail(Integer examRecordId) {
        // 1. 获取考试记录基本信息
        ExamRecord examRecord = this.getById(Long.valueOf(examRecordId));
        if (examRecord == null) {
            throw new RuntimeException("考试记录不存在");
        }

        // 2. 加载试卷信息（包含题目列表）
        Paper paper = paperService.getPaperWithQuestions(examRecord.getExamId());
        examRecord.setPaper(paper);

        // 3. 加载答题记录
        List<AnswerRecord> answerRecords = answerRecordMapper.selectList(
            new QueryWrapper<AnswerRecord>().eq("exam_record_id", examRecordId));
        // 新增：按试卷题目顺序排序答题记录，保证前端展示顺序和考试时一致
        if (paper != null && paper.getQuestions() != null && !answerRecords.isEmpty()) {
            List<Long> questionOrder = paper.getQuestions().stream().map(Question::getId).toList();
            answerRecords.sort((a, b) -> {
                int idxA = questionOrder.indexOf(a.getQuestionId().longValue());
                int idxB = questionOrder.indexOf(b.getQuestionId().longValue());
                return Integer.compare(idxA, idxB);
            });
        }
        examRecord.setAnswerRecords(answerRecords);

        return examRecord;
    }

    /**
     * 标准化判断题答案，将T/F转换为TRUE/FALSE
     * @param answer 原始答案
     * @return 标准化后的答案
     */
    private String normalizeJudgeAnswer(String answer) {
        if (answer == null || answer.trim().isEmpty()) {
            return "";
        }
        
        String normalized = answer.trim().toUpperCase();
        switch (normalized) {
            case "T":
            case "TRUE":
            case "正确":
                return "TRUE";
            case "F":
            case "FALSE":
            case "错误":
                return "FALSE";
            default:
                return normalized;
        }
    }
    
    /**
     * 获取用于显示的答案格式
     * @param answer 数据库中的答案
     * @param questionType 题目类型
     * @return 用于显示的答案
     */
    private String getDisplayAnswer(String answer, String questionType) {
        if ("JUDGE".equals(questionType)) {
            String normalized = normalizeJudgeAnswer(answer);
            switch (normalized) {
                case "TRUE":
                    return "正确(T)";
                case "FALSE":
                    return "错误(F)";
                default:
                    return answer;
            }
        }
        return answer;
    }
} 