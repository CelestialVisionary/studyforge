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
} 