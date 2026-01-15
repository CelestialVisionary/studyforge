package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.common.CacheConstants;
import com.exam.entity.KnowledgePoint;
import com.exam.entity.KnowledgePointQuestion;
import com.exam.entity.Question;
import com.exam.mapper.KnowledgePointMapper;
import com.exam.mapper.KnowledgePointQuestionMapper;
import com.exam.mapper.QuestionMapper;
import com.exam.service.KnowledgePointService;
import com.exam.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 知识点Service实现类 - 实现知识点相关的业务逻辑
 */
@Slf4j
@Service
public class KnowledgePointServiceImpl extends ServiceImpl<KnowledgePointMapper, KnowledgePoint> implements KnowledgePointService {
    
    @Autowired
    private KnowledgePointMapper knowledgePointMapper;
    
    @Autowired
    private KnowledgePointQuestionMapper knowledgePointQuestionMapper;
    
    @Autowired
    private QuestionMapper questionMapper;
    
    @Autowired
    private RedisUtils redisUtils;
    
    /**
     * 保存知识点
     * 保存后清除相关缓存
     */
    @Override
    @Transactional
    @CacheEvict(value = CacheConstants.KNOWLEDGE_POINT_CACHE, allEntries = true)
    public void saveKnowledgePoint(KnowledgePoint knowledgePoint) {
        knowledgePoint.setCreateTime(new Date());
        knowledgePoint.setUpdateTime(new Date());
        this.save(knowledgePoint);
    }
    
    /**
     * 更新知识点
     * 更新后清除相关缓存
     */
    @Override
    @Transactional
    @CacheEvict(value = CacheConstants.KNOWLEDGE_POINT_CACHE, allEntries = true)
    public void updateKnowledgePoint(KnowledgePoint knowledgePoint) {
        knowledgePoint.setUpdateTime(new Date());
        this.updateById(knowledgePoint);
    }
    
    /**
     * 根据ID删除知识点
     * 删除后清除相关缓存
     */
    @Override
    @Transactional
    @CacheEvict(value = CacheConstants.KNOWLEDGE_POINT_CACHE, allEntries = true)
    public void deleteKnowledgePoint(Long id) {
        this.removeById(id);
        // 同时删除知识点与题目的关联关系
        QueryWrapper<KnowledgePointQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("knowledge_point_id", id);
        knowledgePointQuestionMapper.delete(queryWrapper);
    }
    
    /**
     * 根据ID获取知识点详情
     * 使用Redis缓存优化，减少数据库查询
     */
    @Override
    @Cacheable(value = CacheConstants.KNOWLEDGE_POINT_CACHE, key = "'detail:' + #id", unless = "#result == null")
    public KnowledgePoint getKnowledgePointById(Long id) {
        KnowledgePoint knowledgePoint = this.getById(id);
        if (knowledgePoint != null) {
            // 异步增加知识点访问计数
            new Thread(() -> incrementKnowledgePointViewCount(id)).start();
        }
        return knowledgePoint;
    }
    
    /**
     * 根据分类ID获取知识点列表
     * 使用Redis缓存优化，减少数据库查询
     */
    @Override
    @Cacheable(value = CacheConstants.KNOWLEDGE_POINT_CACHE, key = "'category:' + #categoryId", unless = "#result == null || #result.isEmpty()")
    public List<KnowledgePoint> getKnowledgePointsByCategory(Long categoryId) {
        QueryWrapper<KnowledgePoint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        return this.list(queryWrapper);
    }
    
    /**
     * 为知识点添加题目
     * 添加后清除相关缓存
     */
    @Override
    @Transactional
    @CacheEvict(value = CacheConstants.KNOWLEDGE_POINT_CACHE, allEntries = true)
    public void addQuestionToKnowledgePoint(Long knowledgePointId, Long questionId) {
        // 检查知识点和题目是否存在
        if (this.getById(knowledgePointId) == null || questionMapper.selectById(questionId) == null) {
            throw new RuntimeException("知识点或题目不存在");
        }
        
        // 检查关联是否已存在
        QueryWrapper<KnowledgePointQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("knowledge_point_id", knowledgePointId)
                   .eq("question_id", questionId);
        if (knowledgePointQuestionMapper.exists(queryWrapper)) {
            return; // 关联已存在，无需重复添加
        }
        
        // 添加关联
        KnowledgePointQuestion knowledgePointQuestion = new KnowledgePointQuestion();
        knowledgePointQuestion.setKnowledgePointId(knowledgePointId);
        knowledgePointQuestion.setQuestionId(questionId);
        knowledgePointQuestionMapper.insert(knowledgePointQuestion);
    }
    
    /**
     * 为知识点批量添加题目
     * 添加后清除相关缓存
     */
    @Override
    @Transactional
    @CacheEvict(value = CacheConstants.KNOWLEDGE_POINT_CACHE, allEntries = true)
    public void batchAddQuestionsToKnowledgePoint(Long knowledgePointId, List<Long> questionIds) {
        if (CollectionUtils.isEmpty(questionIds)) {
            return;
        }
        
        // 检查知识点是否存在
        if (this.getById(knowledgePointId) == null) {
            throw new RuntimeException("知识点不存在");
        }
        
        // 批量添加关联
        for (Long questionId : questionIds) {
            KnowledgePointQuestion kpq = new KnowledgePointQuestion(knowledgePointId, questionId);
            knowledgePointQuestionMapper.insert(kpq);
        }
    }
    
    /**
     * 从知识点中移除题目
     * 移除后清除相关缓存
     */
    @Override
    @Transactional
    @CacheEvict(value = CacheConstants.KNOWLEDGE_POINT_CACHE, allEntries = true)
    public void removeQuestionFromKnowledgePoint(Long knowledgePointId, Long questionId) {
        QueryWrapper<KnowledgePointQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("knowledge_point_id", knowledgePointId)
                   .eq("question_id", questionId);
        knowledgePointQuestionMapper.delete(queryWrapper);
    }
    
    /**
     * 根据知识点ID获取相关题目列表
     * 使用Redis缓存优化，减少数据库查询
     */
    @Override
    @Cacheable(value = CacheConstants.KNOWLEDGE_POINT_CACHE, key = "'questions:' + #knowledgePointId", unless = "#result == null || #result.isEmpty()")
    public List<Question> getQuestionsByKnowledgePoint(Long knowledgePointId) {
        // 获取关联的题目ID列表
        List<Long> questionIds = knowledgePointQuestionMapper.selectQuestionIdsByKnowledgePointId(knowledgePointId);
        if (CollectionUtils.isEmpty(questionIds)) {
            return List.of();
        }
        
        // 查询题目详情
        return questionMapper.selectBatchIds(questionIds);
    }
    
    /**
     * 根据题目ID获取相关知识点列表
     * 使用Redis缓存优化，减少数据库查询
     */
    @Override
    @Cacheable(value = CacheConstants.KNOWLEDGE_POINT_CACHE, key = "'question_knowledge_points:' + #questionId", unless = "#result == null || #result.isEmpty()")
    public List<KnowledgePoint> getKnowledgePointsByQuestion(Long questionId) {
        // 获取关联的知识点ID列表
        List<Long> knowledgePointIds = knowledgePointQuestionMapper.selectKnowledgePointIdsByQuestionId(questionId);
        if (CollectionUtils.isEmpty(knowledgePointIds)) {
            return List.of();
        }
        
        // 查询知识点详情
        return this.listByIds(knowledgePointIds);
    }
    
    /**
     * 增加知识点访问计数
     * 使用Redis Sorted Set记录知识点访问次数
     */
    @Override
    public void incrementKnowledgePointViewCount(Long knowledgePointId) {
        try {
            // 检查知识点是否存在
            if (this.getById(knowledgePointId) == null) {
                log.warn("尝试增加不存在知识点的访问计数，知识点ID: {}", knowledgePointId);
                return;
            }
            
            // 增加知识点访问计数，如果不存在则初始化为1
            Double newScore = redisUtils.zIncrementScore(CacheConstants.KNOWLEDGE_POINT_VIEW_COUNT_KEY, knowledgePointId, 1);
            log.debug("知识点访问计数增加，知识点ID: {}, 当前计数: {}", knowledgePointId, newScore.intValue());
            
            // 更新知识点热度
            KnowledgePoint knowledgePoint = new KnowledgePoint();
            knowledgePoint.setId(knowledgePointId);
            knowledgePoint.setHot(newScore.intValue());
            knowledgePoint.setUpdateTime(new Date());
            this.updateById(knowledgePoint);
        } catch (Exception e) {
            // 访问计数失败不应影响正常业务
            log.error("增加知识点访问计数失败，知识点ID: {}", knowledgePointId, e);
        }
    }
    
    /**
     * 获取热门知识点列表
     * 根据访问次数排序，获取访问次数最多的知识点
     */
    @Override
    public List<KnowledgePoint> getPopularKnowledgePoints(int limit) {
        log.debug("获取热门知识点，数量: {}", limit);
        
        // 默认获取数量
        if (limit <= 0) {
            limit = CacheConstants.POPULAR_KNOWLEDGE_POINTS_COUNT;
        }
        
        try {
            // 获取访问次数最多的知识点ID列表
            List<Object> popularKnowledgePointIds = redisUtils.zReverseRange(
                CacheConstants.KNOWLEDGE_POINT_VIEW_COUNT_KEY, 0, limit - 1).stream().collect(Collectors.toList());
            
            if (CollectionUtils.isEmpty(popularKnowledgePointIds)) {
                log.debug("没有热门知识点记录，返回最新知识点作为备选方案");
                // 返回最新的知识点
                return this.list(new QueryWrapper<KnowledgePoint>()
                    .orderByDesc("create_time")
                    .last("LIMIT " + limit));
            }
            
            // 将Object类型转换为Long类型
            List<Long> ids = popularKnowledgePointIds.stream()
                .map(id -> {
                    try {
                        return Long.valueOf(id.toString());
                    } catch (NumberFormatException e) {
                        log.warn("知识点ID转换失败: {}", id);
                        return null;
                    }
                })
                .filter(id -> id != null)
                .collect(Collectors.toList());
            
            if (CollectionUtils.isEmpty(ids)) {
                log.warn("转换后的知识点ID列表为空，返回最新知识点作为备选方案");
                // 返回最新的知识点
                return this.list(new QueryWrapper<KnowledgePoint>()
                    .orderByDesc("create_time")
                    .last("LIMIT " + limit));
            }
            
            // 批量查询知识点详情
            return this.listByIds(ids);
        } catch (Exception e) {
            log.error("获取热门知识点失败: {}", e.getMessage(), e);
            // 如果Redis出现问题，返回最新的知识点作为备选方案
            return this.list(new QueryWrapper<KnowledgePoint>()
                .orderByDesc("create_time")
                .last("LIMIT " + limit));
        }
    }
}