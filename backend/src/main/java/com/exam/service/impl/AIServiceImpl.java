package com.exam.service.impl;

import com.exam.dto.ai.ChatRequest;
import com.exam.dto.ai.ChatResponse;
import com.exam.dto.ai.ChatMessage;
import com.exam.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * AI服务实现类
 */
@Service
public class AIServiceImpl implements AIService {

    @Autowired
    private RestTemplate restTemplate; // 用于发送HTTP请求

    @Value("${kimi.api.api-key}")
    private String kimiApiKey; // 从配置文件读取Kimi API Key

    @Value("${kimi.api.base-url}")
    private String kimiBaseUrl; // 从配置文件读取Kimi API基础URL

    @Value("${kimi.api.model}")
    private String model; // 模型名称

    @Value("${kimi.api.temperature}")
    private Double temperature; // 温度参数

    @Value("${kimi.api.max-tokens}")
    private Integer maxTokens; // 最大token数

    /**
     * 获取AI聊天模型的响应
     * @param messages 聊天消息列表
     * @return AI的响应内容
     */
    @Override
    public String getChatCompletion(List<ChatMessage> messages) {
        try {
            // 1. 构建请求头
            HttpHeaders headers = new HttpHeaders(); // 创建HTTP头
            headers.setContentType(MediaType.APPLICATION_JSON); // 设置内容类型为JSON
            headers.setBearerAuth(kimiApiKey); // 设置认证头

            // 2. 构建请求体
            ChatRequest request = ChatRequest.builder()
                    .model(model) // 指定使用的模型
                    .messages(messages) // 设置消息列表
                    .temperature(temperature) // 设置温度参数
                    .maxTokens(maxTokens) // 设置最大token数
                    .build();

            // 3. 发送请求到聊天完成接口
            String apiUrl = kimiBaseUrl + "/chat/completions"; // 构建完整的API URL
            HttpEntity<ChatRequest> httpEntity = new HttpEntity<>(request, headers); // 创建HTTP实体
            ChatResponse response = restTemplate.postForObject(apiUrl, httpEntity, ChatResponse.class); // 发送POST请求

            // 4. 处理响应
            if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                return response.getChoices().get(0).getMessage().getContent(); // 返回第一个选项的消息内容
            }

            return "AI服务暂不可用，请稍后再试。"; // 返回默认错误信息
        } catch (Exception e) {
            // 异常处理，记录错误并返回友好提示
            System.err.println("AI服务调用失败: " + e.getMessage());
            return "AI服务暂时不可用，请稍后再试。";
        }
    }
} 