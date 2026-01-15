package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.KnowledgePointQuestion;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 知识点与题目关联Mapper接口 - 用于知识点与题目的关联操作
 */
public interface KnowledgePointQuestionMapper extends BaseMapper<KnowledgePointQuestion> {
    
    /**
     * 根据知识点ID查询相关题目ID列表
     * @param knowledgePointId 知识点ID
     * @return 题目ID列表
     */
    List<Long> selectQuestionIdsByKnowledgePointId(@Param("knowledgePointId") Long knowledgePointId);
    
    /**
     * 根据题目ID查询相关知识点ID列表
     * @param questionId 题目ID
     * @return 知识点ID列表
     */
    List<Long> selectKnowledgePointIdsByQuestionId(@Param("questionId") Long questionId);
}