package com.exam.service;

import com.exam.dto.ai.ChatMessage;

import java.util.List;

/**
 * AI服务接口
 */
public interface AIService {

    /**
     * 获取AI聊天模型的响应
     * @param messages 聊天消息列表
     * @return AI的响应内容
     */
    String getChatCompletion(List<ChatMessage> messages);

    /**
     * 获取AI聊天模型的响应
     * @param messages 聊天消息列表
     * @param modelType 模型类型："kimi" 或 "gpt" 或 "aimodule"
     * @return AI的响应内容
     */
    String getChatCompletion(List<ChatMessage> messages, String modelType);

    /**
     * 智能答疑功能
     * @param question 用户问题
     * @return AI回答
     */
    String getSmartAnswer(String question);

    /**
     * 智能答疑功能
     * @param question 用户问题
     * @param modelType 模型类型："kimi" 或 "gpt" 或 "aimodule"
     * @return AI回答
     */
    String getSmartAnswer(String question, String modelType);
} 