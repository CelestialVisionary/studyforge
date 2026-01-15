package com.exam.controller;

import com.exam.common.Result;
import com.exam.entity.KnowledgePoint;
import com.exam.entity.Question;
import com.exam.service.KnowledgePointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 知识点控制器 - 处理知识点相关请求
 */
@RestController
@RequestMapping("/api/knowledge-points")
@Tag(name = "知识点管理", description = "知识点相关接口")
@Slf4j
public class KnowledgePointController {
    
    private final KnowledgePointService knowledgePointService;
    
    @Autowired
    public KnowledgePointController(KnowledgePointService knowledgePointService) {
        this.knowledgePointService = knowledgePointService;
    }
    
    /**
     * 保存知识点请求DTO
     */
    @Data
    public static class SaveKnowledgePointRequest {
        private String name;
        private String description;
        private Long categoryId;
    }
    
    /**
     * 知识点关联题目请求DTO
     */
    @Data
    public static class AssociateQuestionRequest {
        private Long knowledgePointId;
        private Long questionId;
    }
    
    /**
     * 知识点批量关联题目请求DTO
     */
    @Data
    public static class BatchAssociateQuestionsRequest {
        private Long knowledgePointId;
        private List<Long> questionIds;
    }
    
    /**
     * 创建知识点
     * @param request 知识点信息
     * @return 创建结果
     */
    @PostMapping
    @Operation(summary = "创建知识点", description = "创建新的知识点")
    public ResponseEntity<Result<KnowledgePoint>> createKnowledgePoint(@RequestBody SaveKnowledgePointRequest request) {
        try {
            KnowledgePoint knowledgePoint = new KnowledgePoint();
            knowledgePoint.setName(request.getName());
            knowledgePoint.setDescription(request.getDescription());
            knowledgePoint.setCategoryId(request.getCategoryId());
            
            knowledgePointService.saveKnowledgePoint(knowledgePoint);
            
            return ResponseEntity.ok(Result.success(knowledgePoint, "知识点创建成功"));
        } catch (Exception e) {
            log.error("创建知识点失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("知识点创建失败: " + e.getMessage()));
        }
    }
    
    /**
     * 更新知识点
     * @param id 知识点ID
     * @param request 知识点信息
     * @return 更新结果
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新知识点", description = "更新知识点信息")
    public ResponseEntity<Result<KnowledgePoint>> updateKnowledgePoint(@PathVariable Long id, @RequestBody SaveKnowledgePointRequest request) {
        try {
            KnowledgePoint knowledgePoint = knowledgePointService.getById(id);
            if (knowledgePoint == null) {
                return ResponseEntity.ok(Result.error("知识点不存在"));
            }
            
            knowledgePoint.setName(request.getName());
            knowledgePoint.setDescription(request.getDescription());
            knowledgePoint.setCategoryId(request.getCategoryId());
            
            knowledgePointService.updateKnowledgePoint(knowledgePoint);
            
            return ResponseEntity.ok(Result.success(knowledgePoint, "知识点更新成功"));
        } catch (Exception e) {
            log.error("更新知识点失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("知识点更新失败: " + e.getMessage()));
        }
    }
    
    /**
     * 删除知识点
     * @param id 知识点ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除知识点", description = "根据ID删除知识点")
    public ResponseEntity<Result<Void>> deleteKnowledgePoint(@PathVariable Long id) {
        try {
            knowledgePointService.deleteKnowledgePoint(id);
            return ResponseEntity.ok(Result.success(null, "知识点删除成功"));
        } catch (Exception e) {
            log.error("删除知识点失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("知识点删除失败: " + e.getMessage()));
        }
    }
    
    /**
     * 获取知识点详情
     * @param id 知识点ID
     * @return 知识点详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取知识点详情", description = "根据ID获取知识点详情")
    public ResponseEntity<Result<KnowledgePoint>> getKnowledgePointById(@PathVariable Long id) {
        try {
            KnowledgePoint knowledgePoint = knowledgePointService.getKnowledgePointById(id);
            if (knowledgePoint == null) {
                return ResponseEntity.ok(Result.error("知识点不存在"));
            }
            return ResponseEntity.ok(Result.success(knowledgePoint, "知识点查询成功"));
        } catch (Exception e) {
            log.error("获取知识点详情失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("知识点查询失败: " + e.getMessage()));
        }
    }
    
    /**
     * 根据分类ID获取知识点列表
     * @param categoryId 分类ID
     * @return 知识点列表
     */
    @GetMapping("/category/{categoryId}")
    @Operation(summary = "根据分类获取知识点列表", description = "根据分类ID获取知识点列表")
    public ResponseEntity<Result<List<KnowledgePoint>>> getKnowledgePointsByCategory(@PathVariable Long categoryId) {
        try {
            List<KnowledgePoint> knowledgePoints = knowledgePointService.getKnowledgePointsByCategory(categoryId);
            return ResponseEntity.ok(Result.success(knowledgePoints, "知识点列表查询成功"));
        } catch (Exception e) {
            log.error("获取知识点列表失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("知识点列表查询失败: " + e.getMessage()));
        }
    }
    
    /**
     * 为知识点添加题目
     * @param request 关联请求
     * @return 关联结果
     */
    @PostMapping("/associate-question")
    @Operation(summary = "为知识点添加题目", description = "为知识点添加相关题目")
    public ResponseEntity<Result<Void>> associateQuestion(@RequestBody AssociateQuestionRequest request) {
        try {
            knowledgePointService.addQuestionToKnowledgePoint(request.getKnowledgePointId(), request.getQuestionId());
            return ResponseEntity.ok(Result.success(null, "题目关联成功"));
        } catch (Exception e) {
            log.error("题目关联失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("题目关联失败: " + e.getMessage()));
        }
    }
    
    /**
     * 为知识点批量添加题目
     * @param request 批量关联请求
     * @return 批量关联结果
     */
    @PostMapping("/batch-associate-questions")
    @Operation(summary = "为知识点批量添加题目", description = "为知识点批量添加相关题目")
    public ResponseEntity<Result<Void>> batchAssociateQuestions(@RequestBody BatchAssociateQuestionsRequest request) {
        try {
            knowledgePointService.batchAddQuestionsToKnowledgePoint(request.getKnowledgePointId(), request.getQuestionIds());
            return ResponseEntity.ok(Result.success(null, "题目批量关联成功"));
        } catch (Exception e) {
            log.error("题目批量关联失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("题目批量关联失败: " + e.getMessage()));
        }
    }
    
    /**
     * 从知识点中移除题目
     * @param request 关联请求
     * @return 移除结果
     */
    @PostMapping("/disassociate-question")
    @Operation(summary = "从知识点中移除题目", description = "从知识点中移除相关题目")
    public ResponseEntity<Result<Void>> disassociateQuestion(@RequestBody AssociateQuestionRequest request) {
        try {
            knowledgePointService.removeQuestionFromKnowledgePoint(request.getKnowledgePointId(), request.getQuestionId());
            return ResponseEntity.ok(Result.success(null, "题目移除成功"));
        } catch (Exception e) {
            log.error("题目移除失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("题目移除失败: " + e.getMessage()));
        }
    }
    
    /**
     * 根据知识点ID获取相关题目列表
     * @param knowledgePointId 知识点ID
     * @return 题目列表
     */
    @GetMapping("/{knowledgePointId}/questions")
    @Operation(summary = "获取知识点相关题目列表", description = "根据知识点ID获取相关题目列表")
    public ResponseEntity<Result<List<Question>>> getQuestionsByKnowledgePoint(@PathVariable Long knowledgePointId) {
        try {
            List<Question> questions = knowledgePointService.getQuestionsByKnowledgePoint(knowledgePointId);
            return ResponseEntity.ok(Result.success(questions, "相关题目查询成功"));
        } catch (Exception e) {
            log.error("获取相关题目失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("相关题目查询失败: " + e.getMessage()));
        }
    }
    
    /**
     * 获取热门知识点列表
     * @param limit 获取数量
     * @return 热门知识点列表
     */
    @GetMapping("/popular")
    @Operation(summary = "获取热门知识点列表", description = "获取热门知识点列表")
    public ResponseEntity<Result<List<KnowledgePoint>>> getPopularKnowledgePoints(@RequestParam(required = false, defaultValue = "10") Integer limit) {
        try {
            List<KnowledgePoint> popularKnowledgePoints = knowledgePointService.getPopularKnowledgePoints(limit);
            return ResponseEntity.ok(Result.success(popularKnowledgePoints, "热门知识点查询成功"));
        } catch (Exception e) {
            log.error("获取热门知识点失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("热门知识点查询失败: " + e.getMessage()));
        }
    }
}