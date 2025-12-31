package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.entity.ExamRecord;
import com.exam.vo.ExamRankingVO;

import java.util.List;

/**
 * 考试记录Service接口
 * 定义考试记录相关的业务方法
 */
public interface ExamRecordService extends IService<ExamRecord> {
    
    /**
     * 根据试卷ID获取考试记录
     * @param examId 试卷ID
     * @return 考试记录列表
     */
    List<ExamRecord> getRecordsByExamId(Long examId);
    
    /**
     * 根据考生姓名获取考试记录
     * @param studentName 考生姓名
     * @return 考试记录列表
     */
    List<ExamRecord> getRecordsByStudentName(String studentName);
    
    /**
     * 保存考试记录
     * @param examRecord 考试记录
     * @return 是否保存成功
     */
    boolean saveExamRecord(ExamRecord examRecord);
    
    /**
     * 获取考试排行榜 - 优化版本
     * 使用SQL关联查询，一次性获取所有需要的数据，避免N+1查询问题
     * 
     * @param paperId 试卷ID，可选参数，不传则查询所有试卷
     * @param limit 显示数量限制，可选参数，不传则返回所有记录
     * @return 排行榜列表
     */
    List<ExamRankingVO> getExamRankingOptimized(Integer paperId, Integer limit);
} 