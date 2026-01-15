package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.PracticeRecord;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 练习记录Mapper接口 - 用于练习记录的数据库操作
 */
public interface PracticeRecordMapper extends BaseMapper<PracticeRecord> {
    
    /**
     * 根据用户ID获取练习记录列表
     * @param userId 用户ID
     * @return 练习记录列表
     */
    List<PracticeRecord> selectByUserId(@Param("userId") Long userId);
    
    /**
     * 根据用户ID和练习类型获取练习记录列表
     * @param userId 用户ID
     * @param practiceType 练习类型
     * @return 练习记录列表
     */
    List<PracticeRecord> selectByUserIdAndType(@Param("userId") Long userId, @Param("practiceType") String practiceType);
    
    /**
     * 获取用户的练习统计信息
     * @param userId 用户ID
     * @return 练习统计信息
     */
    PracticeStats selectPracticeStats(@Param("userId") Long userId);
    
    /**
     * 练习统计信息内部类
     */
    interface PracticeStats {
        Integer getTotalPracticeCount(); // 总练习次数
        Integer getTotalQuestionCount(); // 总练习题目数
        Integer getTotalCorrectCount(); // 总正确题目数
        Double getAverageScore(); // 平均得分
    }
}