package com.exam.controller;

import com.exam.common.Result;
import com.exam.entity.PracticeRecord;
import com.exam.mapper.PracticeRecordMapper;
import com.exam.service.PracticeRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 练习记录控制器 - 处理练习记录相关请求
 */
@RestController
@RequestMapping("/api/practice-records")
@Tag(name = "练习记录管理", description = "练习记录相关接口")
@Slf4j
public class PracticeRecordController {
    
    private final PracticeRecordService practiceRecordService;
    
    @Autowired
    public PracticeRecordController(PracticeRecordService practiceRecordService) {
        this.practiceRecordService = practiceRecordService;
    }
    
    /**
     * 保存练习记录请求DTO
     */
    @Data
    public static class SavePracticeRecordRequest {
        private Long userId;
        private String practiceType;
        private Long relationId;
        private Integer questionCount;
        private Integer correctCount;
        private Integer wrongCount;
        private Integer averageScore;
        private Integer duration;
    }
    
    /**
     * 创建练习记录
     * @param request 练习记录信息
     * @return 创建结果
     */
    @PostMapping
    @Operation(summary = "创建练习记录", description = "创建新的练习记录")
    public ResponseEntity<Result<PracticeRecord>> createPracticeRecord(@RequestBody SavePracticeRecordRequest request) {
        try {
            PracticeRecord practiceRecord = new PracticeRecord();
            practiceRecord.setUserId(request.getUserId());
            practiceRecord.setPracticeType(request.getPracticeType());
            practiceRecord.setRelationId(request.getRelationId());
            practiceRecord.setQuestionCount(request.getQuestionCount());
            practiceRecord.setCorrectCount(request.getCorrectCount());
            practiceRecord.setWrongCount(request.getWrongCount());
            practiceRecord.setAverageScore(request.getAverageScore());
            practiceRecord.setDuration(request.getDuration());
            
            practiceRecordService.savePracticeRecord(practiceRecord);
            
            return ResponseEntity.ok(Result.success(practiceRecord, "练习记录创建成功"));
        } catch (Exception e) {
            log.error("创建练习记录失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("练习记录创建失败: " + e.getMessage()));
        }
    }
    
    /**
     * 获取用户练习记录列表
     * @param userId 用户ID
     * @return 练习记录列表
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "获取用户练习记录列表", description = "根据用户ID获取练习记录列表")
    public ResponseEntity<Result<List<PracticeRecord>>> getPracticeRecordsByUserId(@PathVariable Long userId) {
        try {
            List<PracticeRecord> practiceRecords = practiceRecordService.getPracticeRecordsByUserId(userId);
            return ResponseEntity.ok(Result.success(practiceRecords, "练习记录列表查询成功"));
        } catch (Exception e) {
            log.error("获取练习记录列表失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("练习记录列表查询失败: " + e.getMessage()));
        }
    }
    
    /**
     * 根据练习类型获取用户练习记录列表
     * @param userId 用户ID
     * @param practiceType 练习类型
     * @return 练习记录列表
     */
    @GetMapping("/user/{userId}/type/{practiceType}")
    @Operation(summary = "根据练习类型获取用户练习记录列表", description = "根据用户ID和练习类型获取练习记录列表")
    public ResponseEntity<Result<List<PracticeRecord>>> getPracticeRecordsByUserIdAndType(@PathVariable Long userId, @PathVariable String practiceType) {
        try {
            List<PracticeRecord> practiceRecords = practiceRecordService.getPracticeRecordsByUserIdAndType(userId, practiceType);
            return ResponseEntity.ok(Result.success(practiceRecords, "练习记录列表查询成功"));
        } catch (Exception e) {
            log.error("获取练习记录列表失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("练习记录列表查询失败: " + e.getMessage()));
        }
    }
    
    /**
     * 获取用户练习统计信息
     * @param userId 用户ID
     * @return 练习统计信息
     */
    @GetMapping("/stats/{userId}")
    @Operation(summary = "获取用户练习统计信息", description = "根据用户ID获取练习统计信息")
    public ResponseEntity<Result<PracticeRecordMapper.PracticeStats>> getPracticeStats(@PathVariable Long userId) {
        try {
            PracticeRecordMapper.PracticeStats practiceStats = practiceRecordService.getPracticeStatsByUserId(userId);
            return ResponseEntity.ok(Result.success(practiceStats, "练习统计信息查询成功"));
        } catch (Exception e) {
            log.error("获取练习统计信息失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("练习统计信息查询失败: " + e.getMessage()));
        }
    }
    
    /**
     * 统计用户练习某知识点的次数
     * @param userId 用户ID
     * @param knowledgePointId 知识点ID
     * @return 练习次数
     */
    @GetMapping("/count/{userId}/knowledge-point/{knowledgePointId}")
    @Operation(summary = "统计用户练习某知识点的次数", description = "统计用户练习某知识点的次数")
    public ResponseEntity<Result<Integer>> countPracticeByKnowledgePoint(@PathVariable Long userId, @PathVariable Long knowledgePointId) {
        try {
            Integer count = practiceRecordService.countPracticeByKnowledgePoint(userId, knowledgePointId);
            return ResponseEntity.ok(Result.success(count, "练习次数统计成功"));
        } catch (Exception e) {
            log.error("统计练习次数失败: {}", e.getMessage(), e);
            return ResponseEntity.ok(Result.error("练习次数统计失败: " + e.getMessage()));
        }
    }
}