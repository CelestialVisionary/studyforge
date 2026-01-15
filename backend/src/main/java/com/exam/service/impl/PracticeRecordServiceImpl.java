package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.PracticeRecord;
import com.exam.mapper.PracticeRecordMapper;
import com.exam.service.PracticeRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 练习记录Service实现类 - 实现练习记录相关的业务逻辑
 */
@Slf4j
@Service
public class PracticeRecordServiceImpl extends ServiceImpl<PracticeRecordMapper, PracticeRecord> implements PracticeRecordService {
    
    @Autowired
    private PracticeRecordMapper practiceRecordMapper;
    
    /**
     * 保存练习记录
     */
    @Override
    @Transactional
    public void savePracticeRecord(PracticeRecord practiceRecord) {
        practiceRecord.setPracticeTime(new Date());
        this.save(practiceRecord);
    }
    
    /**
     * 根据用户ID获取练习记录列表
     */
    @Override
    public List<PracticeRecord> getPracticeRecordsByUserId(Long userId) {
        return practiceRecordMapper.selectByUserId(userId);
    }
    
    /**
     * 根据用户ID和练习类型获取练习记录列表
     */
    @Override
    public List<PracticeRecord> getPracticeRecordsByUserIdAndType(Long userId, String practiceType) {
        return practiceRecordMapper.selectByUserIdAndType(userId, practiceType);
    }
    
    /**
     * 获取用户的练习统计信息
     */
    @Override
    public PracticeRecordMapper.PracticeStats getPracticeStatsByUserId(Long userId) {
        return practiceRecordMapper.selectPracticeStats(userId);
    }
    
    /**
     * 统计用户练习某知识点的次数
     */
    @Override
    public Integer countPracticeByKnowledgePoint(Long userId, Long knowledgePointId) {
        QueryWrapper<PracticeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                   .eq("practice_type", "KNOWLEDGE_POINT")
                   .eq("relation_id", knowledgePointId);
        return Math.toIntExact(this.count(queryWrapper));
    }
}