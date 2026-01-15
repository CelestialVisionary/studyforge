package com.exam.controller;

import com.exam.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * AI控制器 - 处理智能答疑相关请求
 * 
 * @author 智能学习平台开发团队
 * @version 1.0
 */
@RestController
@RequestMapping("/api/ai")
@Tag(name = "AI智能答疑", description = "AI智能答疑相关接口")
public class AiController {
    
    private final AIService aiService;
    
    @Autowired
    public AiController(AIService aiService) {
        this.aiService = aiService;
    }
    
    /**
     * 智能答疑请求DTO
     */
    @Data
    public static class SmartAnswerRequest {
        private String question;
    }
    
    /**
     * 智能答疑响应DTO
     */
    @Data
    public static class SmartAnswerResponse {
        private String answer;
    }
    
    /**
     * 智能答疑功能
     * 
     * @param request 答疑请求
     * @return 答疑响应
     */
    @PostMapping("/smart-answer")
    @Operation(summary = "智能答疑", description = "使用GPT-3.5模型回答用户的学习问题")
    public ResponseEntity<SmartAnswerResponse> getSmartAnswer(@RequestBody SmartAnswerRequest request) {
        // 调用AI服务获取智能回答
        String answer = aiService.getSmartAnswer(request.getQuestion());
        
        // 构建响应
        SmartAnswerResponse response = new SmartAnswerResponse();
        response.setAnswer(answer);
        
        return ResponseEntity.ok(response);
    }
}
