package com.exam.service;

import com.exam.dto.AiGenerateRequestDto;
import com.exam.dto.QuestionImportDto;
import java.util.List;

/**
 * Kimi AI服务接口
 * 用于调用Kimi API生成题目
 */
public interface KimiAiService {
    
    /**
     * 根据要求生成题目
     * @param request AI生成请求参数
     * @return 生成的题目列表
     */
    List<QuestionImportDto> generateQuestions(AiGenerateRequestDto request);
} 