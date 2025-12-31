package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.dto.SubmitAnswerDto;
import com.exam.entity.ExamRecord;

import java.util.List;

/**
 * 考试服务接口
 */
public interface ExamService extends IService<ExamRecord> {

    /**
     * 开始一场考试
     * @param paperId 试卷ID
     * @param studentName 考生姓名
     * @param userId  用户ID
     * @return 考试记录
     */
    ExamRecord startExam(Integer paperId, String studentName, Integer userId);

    /**
     * 提交答案
     * @param examRecordId 考试记录ID
     * @param answers      用户答案列表
     */
    void submitAnswers(Integer examRecordId, List<SubmitAnswerDto> answers);

    /**
     * 批阅试卷（包含AI自动批阅）
     * @param examRecordId 考试记录ID
     * @return 批阅后的考试记录
     */
    ExamRecord gradeExam(Integer examRecordId);

    /**
     * 获取考试记录详情（包含试卷信息和答题记录）
     * @param examRecordId 考试记录ID
     * @return 考试记录详情
     */
    ExamRecord getExamRecordDetail(Integer examRecordId);
} 
 