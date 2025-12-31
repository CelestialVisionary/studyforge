package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.dto.AiPaperDto;
import com.exam.dto.PaperDto;
import com.exam.dto.RuleDto;
import com.exam.entity.Paper;
import com.exam.entity.PaperQuestion;
import com.exam.entity.Question;
import com.exam.entity.QuestionChoice;
import com.exam.entity.QuestionAnswer;
import com.exam.mapper.PaperMapper;
import com.exam.mapper.PaperQuestionMapper;
import com.exam.mapper.QuestionChoiceMapper;
import com.exam.mapper.QuestionMapper;
import com.exam.mapper.QuestionAnswerMapper;
import com.exam.service.AIService;
import com.exam.service.PaperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map; // 导入Map类型，解决未导入导致的编译错误
import java.util.HashMap; // 如果有用到HashMap也一并导入
import java.util.stream.Collectors; // 导入Collectors工具类，支持流式分组和收集

/**
 * 试卷服务实现类
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {

    private static final Logger log = LoggerFactory.getLogger(PaperServiceImpl.class);

    @Autowired
    private PaperQuestionMapper paperQuestionMapper; // 试卷-题目关联表Mapper

    @Autowired
    private QuestionMapper questionMapper; // 题目表Mapper

    @Autowired
    private QuestionChoiceMapper questionChoiceMapper; // 题目选项表Mapper

    @Autowired
    private QuestionAnswerMapper questionAnswerMapper; // 题目答案表Mapper

    @Autowired
    private AIService aiService; // AI服务

    /**
     * 创建试卷（手动组卷）
     * @param paperDto 试卷数据
     * @return 创建好的试卷
     */
    @Override
    @Transactional // 开启事务
    public Paper createPaper(PaperDto paperDto) {
        // 1. 创建试卷对象
        Paper paper = new Paper(); // 实例化Paper对象
        paper.setName(paperDto.getName()); // 设置名称
        paper.setDescription(paperDto.getDescription()); // 设置描述
        paper.setDuration(paperDto.getDuration()); // 设置时长
        paper.setStatus("DRAFT"); // 新建试卷默认为草稿状态

        // 2. 计算总分和题目数量
        BigDecimal totalScore = paperDto.getQuestions().values().stream().reduce(BigDecimal.ZERO, BigDecimal::add); // 计算总分
        paper.setTotalScore(totalScore); // 设置总分
        paper.setQuestionCount(paperDto.getQuestions().size()); // 设置题目数量
        baseMapper.insert(paper); // 插入试卷记录到数据库

        // 3. 批量插入试卷-题目关联记录
        List<PaperQuestion> paperQuestions = paperDto.getQuestions().entrySet().stream()
                .map(entry -> new PaperQuestion(paper.getId(), entry.getKey().longValue(), entry.getValue()))
                .collect(Collectors.toList()); // 构建关联记录列表
        paperQuestions.forEach(paperQuestionMapper::insert); // 批量插入

        return paper; // 返回创建的试卷
    }

    /**
     * 智能创建试卷（AI组卷）
     * @param aiPaperDto 试卷数据
     * @return 创建好的试卷
     */
    @Override
    @Transactional
    public Paper createPaperWithAI(AiPaperDto aiPaperDto) {
        // 1. 创建并保存试卷基本信息
        Paper paper = new Paper();
        paper.setName(aiPaperDto.getName());
        paper.setDescription(aiPaperDto.getDescription());
        paper.setDuration(aiPaperDto.getDuration());
        paper.setStatus("待发布"); // 初始状态
        
        // 补上总分和总题数的计算
        int totalQuestionCount = aiPaperDto.getRules().stream()
                .mapToInt(rule -> rule.getCount() != null ? rule.getCount() : 0)
                .sum();
        BigDecimal totalScore = aiPaperDto.getRules().stream()
                .map(rule -> BigDecimal.valueOf(rule.getScore()).multiply(BigDecimal.valueOf(rule.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        paper.setQuestionCount(totalQuestionCount);
        paper.setTotalScore(totalScore);

        baseMapper.insert(paper);

        // 2. 根据规则抽取题目
        for (RuleDto rule : aiPaperDto.getRules()) {
            if (rule.getCount() == null || rule.getCount() <= 0) {
                log.info("规则 {} 的题目数量为0，已跳过", rule);
                continue; // 如果题目数量为0或null，则跳过此规则
            }
            
            // 构建查询条件
            QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type", rule.getType().name());

            // 如果指定了分类，则加入分类查询条件
            if (rule.getCategoryIds() != null && !rule.getCategoryIds().isEmpty()) {
                queryWrapper.in("category_id", rule.getCategoryIds());
            } else {
                // 如果前端没传分类ID，我们应该查询该大类下的所有题目
                // 这需要一个逻辑来找到大类下的所有子分类ID
                log.warn("规则 {} 没有提供具体的分类ID，将在此类型下所有题目中随机选择", rule.getType());
            }
            
            // 为了避免LIMIT数量大于实际数量的问题，我们先查询ID，再随机选取
            queryWrapper.select("id"); // 只查询ID，提高效率
            List<Long> availableQuestionIds = questionMapper.selectList(queryWrapper)
                                                    .stream()
                                                    .map(Question::getId)
                                                    .collect(Collectors.toList());

            if(availableQuestionIds.isEmpty()){
                log.warn("类型 {} 下没有找到任何题目，跳过此规则", rule.getType());
                continue;
            }

            // 打乱顺序
            Collections.shuffle(availableQuestionIds);
            
            // 取需要的数量，或者全部（如果不够的话）
            int questionToTake = Math.min(rule.getCount(), availableQuestionIds.size());
            List<Long> selectedQuestionIds = availableQuestionIds.subList(0, questionToTake);

            // 根据选中的ID列表获取完整的题目信息
            if(selectedQuestionIds.isEmpty()){
                continue;
            }
            List<Question> questions = questionMapper.selectBatchIds(selectedQuestionIds);
            
            // 3. 组装并插入试卷-题目关联关系
            for (Question question : questions) {
                PaperQuestion pq = new PaperQuestion();
                pq.setPaperId(paper.getId());
                pq.setQuestionId(question.getId());
                pq.setScore(BigDecimal.valueOf(rule.getScore())); // 修正类型
                paperQuestionMapper.insert(pq); // 逐条插入
            }
        }
        
        return paper;
    }

    /**
     * AI智能组卷（旧版）- 保留以兼容旧接口，或可标记为@Deprecated
     */
    @Override
    public Paper createPaperWithAI(Integer categoryId, Integer questionCount, Integer totalScore) {
        // 可以抛出"方法已过时"的异常，或返回一个错误提示
        throw new UnsupportedOperationException("此版本的AI组卷接口已停用，请使用新版接口。");
    }

    /**
     * 获取带题目的试卷详情
     * @param paperId 试卷ID
     * @return 试卷详情
     */
    @Override
    public Paper getPaperWithQuestions(Long paperId) {
        // 1. 查询试卷基本信息
        Paper paper = baseMapper.selectById(paperId); // 查询试卷
        if (paper == null) {
            return null; // 如果试卷不存在，返回null
        }

        // 2. 查询试卷包含的所有题目ID和分值
        QueryWrapper<PaperQuestion> pqWrapper = new QueryWrapper<>();
        pqWrapper.eq("paper_id", paperId); // 设置查询条件
        List<PaperQuestion> paperQuestions = paperQuestionMapper.selectList(pqWrapper); // 查询关联记录
        if (paperQuestions.isEmpty()) {
            paper.setQuestions(new ArrayList<>());
            return paper;
        }
        
        List<Long> questionIds = paperQuestions.stream().map(PaperQuestion::getQuestionId).collect(Collectors.toList());

        // 3. 根据题目ID查询题目详情
        List<Question> questions = questionMapper.selectBatchIds(questionIds);
        
        // 4. 优化：批量查询避免N+1问题
        // 4.1 填充分值信息
        Map<Long, BigDecimal> scoreMap = paperQuestions.stream()
                .collect(Collectors.toMap(PaperQuestion::getQuestionId, PaperQuestion::getScore));
        
        // 批量查询选项
        List<QuestionChoice> allChoices = questionChoiceMapper.selectList(
                new QueryWrapper<QuestionChoice>().in("question_id", questionIds)
        );
        Map<Long, List<QuestionChoice>> choicesMap = allChoices.stream()
                .collect(Collectors.groupingBy(QuestionChoice::getQuestionId));
        
        // 批量查询答案
        List<QuestionAnswer> allAnswers = questionAnswerMapper.selectList(
                new QueryWrapper<QuestionAnswer>().in("question_id", questionIds)
        );
        Map<Long, QuestionAnswer> answersMap = allAnswers.stream()
                .collect(Collectors.toMap(QuestionAnswer::getQuestionId, answer -> answer));
        
        // 4.3 为每个题目设置信息
        questions.forEach(q -> {
            // 设置分值
            q.setPaperScore(scoreMap.get(q.getId()));
            
            // 设置选项（仅选择题）
            if ("CHOICE".equals(q.getType())) {
                List<QuestionChoice> choices = choicesMap.getOrDefault(q.getId(), new ArrayList<>());
                // 按sort字段排序
                choices.sort((c1, c2) -> Integer.compare(
                    c1.getSort() != null ? c1.getSort() : 0,
                    c2.getSort() != null ? c2.getSort() : 0
                ));
                q.setChoices(choices);
            }
            
            // 设置答案
            q.setAnswer(answersMap.get(q.getId()));
        });
        
        // 5. 按题目类型排序：选择题 -> 判断题 -> 简答题
        questions.sort((q1, q2) -> {
            int order1 = getTypeOrder(q1.getType());
            int order2 = getTypeOrder(q2.getType());
            return Integer.compare(order1, order2);
        });
        
        paper.setQuestions(questions); // 设置题目列表

        return paper; // 返回试卷详情
    }
    
    /**
     * 获取题目类型的排序顺序
     * @param type 题目类型
     * @return 排序序号
     */
    private int getTypeOrder(String type) {
        switch (type) {
            case "CHOICE": return 1; // 选择题
            case "JUDGE": return 2;  // 判断题
            case "TEXT": return 3;   // 简答题
            default: return 4;       // 其他类型
        }
    }

    @Override
    public void updatePaperStatus(Integer paperId, String status) {
        Paper paper = new Paper();
        paper.setId(paperId);
        paper.setStatus(status);
        this.updateById(paper);
    }

    @Override
    @Transactional
    public Paper updatePaper(Integer paperId, PaperDto paperDto) {
        // 1. 更新试卷基本信息
        Paper paper = new Paper();
        paper.setId(paperId);
        paper.setName(paperDto.getName());
        paper.setDescription(paperDto.getDescription());
        paper.setDuration(paperDto.getDuration());

        // 2. 重新计算总分和题目数
        BigDecimal totalScore = paperDto.getQuestions().values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        paper.setTotalScore(totalScore);
        paper.setQuestionCount(paperDto.getQuestions().size());
        baseMapper.updateById(paper);

        // 3. 删除旧的试卷-题目关联
        paperQuestionMapper.delete(new QueryWrapper<PaperQuestion>().eq("paper_id", paperId));

        // 4. 插入新的试卷-题目关联
        List<PaperQuestion> paperQuestions = paperDto.getQuestions().entrySet().stream()
                .map(entry -> new PaperQuestion(paperId, entry.getKey().longValue(), entry.getValue()))
                .collect(Collectors.toList());
        paperQuestions.forEach(paperQuestionMapper::insert);

        return paper;
    }
} 