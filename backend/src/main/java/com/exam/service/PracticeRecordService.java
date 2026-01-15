package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.entity.PracticeRecord;
import com.exam.mapper.PracticeRecordMapper;

import java.util.List;

/**
 * 练习记录Service接口 - 定义练习记录相关的业务逻辑
 */
public interface PracticeRecordService extends IService<PracticeRecord> {
    
    /**
     * 保存练习记录
     * @param practiceRecord 练习记录信息
     */
    void savePracticeRecord(PracticeRecord practiceRecord);
    
    /**
     * 根据用户ID获取练习记录列表
     * @param userId 用户ID
     * @return 练习记录列表
     */
    List<PracticeRecord> getPracticeRecordsByUserId(Long userId);
    
    /**
     * 根据用户ID和练习类型获取练习记录列表
     * @param userId 用户ID
     * @param practiceType 练习类型
     * @return 练习记录列表
     */
    List<PracticeRecord> getPracticeRecordsByUserIdAndType(Long userId, String practiceType);
    
    /**
     * 获取用户的练习统计信息
     * @param userId 用户ID
     * @return 练习统计信息
     */
    PracticeRecordMapper.PracticeStats getPracticeStatsByUserId(Long userId);
    
    /**
     * 统计用户练习某知识点的次数
     * @param userId 用户ID
     * @param knowledgePointId 知识点ID
     * @return 练习次数
     */
    Integer countPracticeByKnowledgePoint(Long userId, Long knowledgePointId);
}