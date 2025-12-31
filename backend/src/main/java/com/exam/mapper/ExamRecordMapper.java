package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.ExamRecord;
import com.exam.vo.ExamRankingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 86158
 * @description 针对表【exam_record(考试记录表)】的数据库操作Mapper
 * @createDate 2025-06-20 22:37:43
 * @Entity com.exam.entity.ExamRecord
 */
@Mapper
public interface ExamRecordMapper extends BaseMapper<ExamRecord> {
    
    /**
     * 获取考试排行榜 - 使用SQL关联查询优化性能
     * 一次查询获取所有需要的数据，避免N+1查询问题
     * 
     * @param paperId 试卷ID，可选参数
     * @param limit 显示数量限制，可选参数
     * @return 排行榜列表
     */
    List<ExamRankingVO> selectExamRanking(@Param("paperId") Integer paperId, @Param("limit") Integer limit);
} 