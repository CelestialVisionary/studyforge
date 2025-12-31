package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.ExamRecord;
import com.exam.mapper.ExamRecordMapper;
import com.exam.service.ExamRecordService;
import com.exam.vo.ExamRankingVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考试记录Service实现类
 * 实现考试记录相关的业务逻辑
 */
@Service
public class ExamRecordServiceImpl extends ServiceImpl<ExamRecordMapper, ExamRecord> implements ExamRecordService {
    
    @Override
    public List<ExamRecord> getRecordsByExamId(Long examId) {
        // 根据试卷ID查询考试记录
        QueryWrapper<ExamRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("exam_id", examId)
                   .orderByDesc("create_time");
        return this.list(queryWrapper);
    }
    
    @Override
    public List<ExamRecord> getRecordsByStudentName(String studentName) {
        // 根据考生姓名查询考试记录
        QueryWrapper<ExamRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_name", studentName);
        queryWrapper.orderByDesc("create_time");
        return this.list(queryWrapper);
    }
    
    @Override
    public boolean saveExamRecord(ExamRecord examRecord) {
        // 保存考试记录
        return this.save(examRecord);
    }
    
    @Override
    public List<ExamRankingVO> getExamRankingOptimized(Integer paperId, Integer limit) {
        // 使用优化的SQL关联查询获取排行榜数据
        // 一次查询解决N+1问题，大幅提升性能
        return baseMapper.selectExamRanking(paperId, limit);
    }
} 