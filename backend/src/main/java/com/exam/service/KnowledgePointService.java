package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.entity.KnowledgePoint;
import com.exam.entity.Question;

import java.util.List;

/**
 * 知识点Service接口 - 定义知识点相关的业务逻辑
 */
public interface KnowledgePointService extends IService<KnowledgePoint> {
    
    /**
     * 保存知识点
     * @param knowledgePoint 知识点信息
     */
    void saveKnowledgePoint(KnowledgePoint knowledgePoint);
    
    /**
     * 更新知识点
     * @param knowledgePoint 知识点信息
     */
    void updateKnowledgePoint(KnowledgePoint knowledgePoint);
    
    /**
     * 根据ID删除知识点
     * @param id 知识点ID
     */
    void deleteKnowledgePoint(Long id);
    
    /**
     * 根据ID获取知识点详情
     * @param id 知识点ID
     * @return 知识点详情
     */
    KnowledgePoint getKnowledgePointById(Long id);
    
    /**
     * 根据分类ID获取知识点列表
     * @param categoryId 分类ID
     * @return 知识点列表
     */
    List<KnowledgePoint> getKnowledgePointsByCategory(Long categoryId);
    
    /**
     * 为知识点添加题目
     * @param knowledgePointId 知识点ID
     * @param questionId 题目ID
     */
    void addQuestionToKnowledgePoint(Long knowledgePointId, Long questionId);
    
    /**
     * 为知识点批量添加题目
     * @param knowledgePointId 知识点ID
     * @param questionIds 题目ID列表
     */
    void batchAddQuestionsToKnowledgePoint(Long knowledgePointId, List<Long> questionIds);
    
    /**
     * 从知识点中移除题目
     * @param knowledgePointId 知识点ID
     * @param questionId 题目ID
     */
    void removeQuestionFromKnowledgePoint(Long knowledgePointId, Long questionId);
    
    /**
     * 根据知识点ID获取相关题目列表
     * @param knowledgePointId 知识点ID
     * @return 题目列表
     */
    List<Question> getQuestionsByKnowledgePoint(Long knowledgePointId);
    
    /**
     * 根据题目ID获取相关知识点列表
     * @param questionId 题目ID
     * @return 知识点列表
     */
    List<KnowledgePoint> getKnowledgePointsByQuestion(Long questionId);
    
    /**
     * 增加知识点访问计数
     * @param knowledgePointId 知识点ID
     */
    void incrementKnowledgePointViewCount(Long knowledgePointId);
    
    /**
     * 获取热门知识点列表
     * @param limit 获取数量
     * @return 热门知识点列表
     */
    List<KnowledgePoint> getPopularKnowledgePoints(int limit);
}