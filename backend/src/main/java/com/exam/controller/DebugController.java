package com.exam.controller;

import com.exam.common.Result;
import com.exam.entity.ExamRecord;
import com.exam.entity.Paper;
import com.exam.entity.AnswerRecord;
import com.exam.service.ExamService;
import com.exam.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 调试控制器 - 用于检查数据问题
 */
@RestController
@RequestMapping("/api/debug")
public class DebugController {

    @Autowired
    private ExamService examService;
    
    @Autowired
    private PaperService paperService;

    /**
     * 调试考试记录数据
     */
    @GetMapping("/exam-record/{examRecordId}")
    public Result<Map<String, Object>> debugExamRecord(@PathVariable Integer examRecordId) {
        Map<String, Object> debugInfo = new HashMap<>();
        
        try {
            // 1. 获取考试记录
            ExamRecord examRecord = examService.getExamRecordDetail(examRecordId);
            debugInfo.put("examRecord", examRecord);
            
            // 2. 获取试卷信息
            Paper paper = paperService.getPaperWithQuestions(examRecord.getExamId());
            debugInfo.put("paper", paper);
            
            // 3. 计算统计信息
            Map<String, Object> stats = new HashMap<>();
            stats.put("examScore", examRecord.getScore());
            stats.put("paperTotalScore", paper.getTotalScore());
            stats.put("paperQuestionCount", paper.getQuestionCount());
            stats.put("actualAnswerCount", examRecord.getAnswerRecords().size());
            
            // 4. 分析答题情况
            int correctCount = 0;
            int wrongCount = 0;
            int partialCount = 0;
            int totalCalculatedScore = 0;
            
            for (AnswerRecord record : examRecord.getAnswerRecords()) {
                if (record.getIsCorrect() == 1) correctCount++;
                else if (record.getIsCorrect() == 0) wrongCount++;
                else if (record.getIsCorrect() == 2) partialCount++;
                
                totalCalculatedScore += (record.getScore() != null ? record.getScore() : 0);
            }
            
            stats.put("correctCount", correctCount);
            stats.put("wrongCount", wrongCount);
            stats.put("partialCount", partialCount);
            stats.put("calculatedTotalScore", totalCalculatedScore);
            
            debugInfo.put("statistics", stats);
            
            // 5. 检查题目分值
            Map<String, Object> questionScores = new HashMap<>();
            for (AnswerRecord record : examRecord.getAnswerRecords()) {
                Map<String, Object> questionInfo = new HashMap<>();
                questionInfo.put("questionId", record.getQuestionId());
                questionInfo.put("userAnswer", record.getUserAnswer());
                questionInfo.put("score", record.getScore());
                questionInfo.put("isCorrect", record.getIsCorrect());
                
                // 找到对应的题目信息
                paper.getQuestions().stream()
                    .filter(q -> q.getId().equals(record.getQuestionId().longValue()))
                    .findFirst()
                    .ifPresent(q -> {
                        questionInfo.put("paperScore", q.getPaperScore());
                        questionInfo.put("questionType", q.getType());
                        questionInfo.put("standardAnswer", q.getAnswer() != null ? q.getAnswer().getAnswer() : "无答案");
                    });
                
                questionScores.put("question_" + record.getQuestionId(), questionInfo);
            }
            
            debugInfo.put("questionDetails", questionScores);
            
            return Result.success(debugInfo, "调试信息获取成功");
            
        } catch (Exception e) {
            debugInfo.put("error", e.getMessage());
            return Result.error("调试信息获取失败: " + e.getMessage());
        }
    }
} 