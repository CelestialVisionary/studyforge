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

    // Kimi AI配置
    @Value("${kimi.api.api-key}")
    private String kimiApiKey;
    @Value("${kimi.api.base-url}")
    private String kimiBaseUrl;
    @Value("${kimi.api.model}")
    private String kimiModel;
    @Value("${kimi.api.temperature}")
    private Double kimiTemperature;
    @Value("${kimi.api.max-tokens}")
    private Integer kimiMaxTokens;

    // GPT-3.5配置
    @Value("${gpt.api.api-key}")
    private String gptApiKey;
    @Value("${gpt.api.base-url}")
    private String gptBaseUrl;
    @Value("${gpt.api.model}")
    private String gptModel;
    @Value("${gpt.api.temperature}")
    private Double gptTemperature;
    @Value("${gpt.api.max-tokens}")
    private Integer gptMaxTokens;

    /**
     * 获取AI聊天模型的响应
     * @param messages 聊天消息列表
     * @return AI的响应内容
     */
    @Override
    public String getChatCompletion(List<ChatMessage> messages) {
        return getChatCompletion(messages, "kimi"); // 默认使用Kimi AI
    }

    /**
     * 获取AI聊天模型的响应
     * @param messages 聊天消息列表
     * @param modelType 模型类型："kimi" 或 "gpt"
     * @return AI的响应内容
     */
    public String getChatCompletion(List<ChatMessage> messages, String modelType) {
        try {
            // 根据模型类型选择配置
            String apiKey, baseUrl, model, temperature, maxTokens;
            if ("gpt".equalsIgnoreCase(modelType)) {
                apiKey = gptApiKey;
                baseUrl = gptBaseUrl;
                model = gptModel;
                temperature = gptTemperature.toString();
                maxTokens = gptMaxTokens.toString();
            } else {
                // 默认使用Kimi AI
                apiKey = kimiApiKey;
                baseUrl = kimiBaseUrl;
                model = kimiModel;
                temperature = kimiTemperature.toString();
                maxTokens = kimiMaxTokens.toString();
            }

            // 1. 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            // 2. 构建请求体
            ChatRequest request = ChatRequest.builder()
                    .model(model)
                    .messages(messages)
                    .temperature(Double.parseDouble(temperature))
                    .maxTokens(Integer.parseInt(maxTokens))
                    .build();

            // 3. 发送请求到聊天完成接口
            String apiUrl = baseUrl + "/chat/completions";
            HttpEntity<ChatRequest> httpEntity = new HttpEntity<>(request, headers);
            ChatResponse response = restTemplate.postForObject(apiUrl, httpEntity, ChatResponse.class);

            // 4. 处理响应
            if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                return response.getChoices().get(0).getMessage().getContent();
            }

            return "AI服务暂不可用，请稍后再试。";
        } catch (Exception e) {
            System.err.println("AI服务调用失败: " + e.getMessage());
            return "AI服务暂时不可用，请稍后再试。";
        }
    }

    /**
     * 智能答疑功能 - 使用GPT-3.5模型
     * @param question 用户问题
     * @return AI回答
     */
    public String getSmartAnswer(String question) {
        // 构建系统提示
        ChatMessage systemMessage = new ChatMessage();
        systemMessage.setRole("system");
        systemMessage.setContent("你是一个专业的智能学习助手，专注于回答学生的学习问题，准确率要求达到85%以上。请严格遵循以下要求：\n" +
                "1. 角色定位：你是一位知识渊博、耐心细致的学习导师，熟悉各类学科知识，尤其是计算机科学、数学、物理等基础学科。\n" +
                "2. 回答原则：\n" +
                "   - 准确性：确保回答内容准确无误，引用权威来源，避免传播错误信息。\n" +
                "   - 简洁性：回答要简洁明了，避免冗长的表述，突出重点。\n" +
                "   - 易懂性：使用通俗易懂的语言，避免过于专业的术语，必要时进行解释。\n" +
                "   - 结构化：采用分点作答的方式，使回答条理清晰，便于理解。\n" +
                "3. 回答格式：\n" +
                "   - 对于概念性问题：先给出明确的定义，再进行解释和举例。\n" +
                "   - 对于解题类问题：先给出解题思路，再逐步推导，最后给出答案。\n" +
                "   - 对于比较类问题：列出对比点，分别说明，最后总结。\n" +
                "4. 不确定情况处理：如果对问题的答案不确定，明确说明，并建议学生查阅相关权威资料或咨询老师。\n" +
                "5. 知识范围：只回答与学习相关的问题，对于无关问题礼貌拒绝。\n" +
                "6. 示例回答：\n" +
                "   问题：什么是Java中的多态？\n" +
                "   回答：\n" +
                "   1. 定义：多态是Java面向对象编程的三大特性之一，指同一个方法调用可以根据对象的不同而表现出不同的行为。\n" +
                "   2. 实现方式：\n" +
                "      - 方法重写（Override）：子类重写父类的方法\n" +
                "      - 方法重载（Overload）：同一个类中同名但参数不同的方法\n" +
                "   3. 示例：\n" +
                "      ```java\n" +
                "      // 父类\n" +
                "      class Animal {\n" +
                "          public void makeSound() {\n" +
                "              System.out.println(\"Animal makes sound\");\n" +
                "          }\n" +
                "      }\n" +
                "      // 子类\n" +
                "      class Dog extends Animal {\n" +
                "          @Override\n" +
                "          public void makeSound() {\n" +
                "              System.out.println(\"Dog barks\");\n" +
                "          }\n" +
                "      }\n" +
                "      // 测试\n" +
                "      Animal animal = new Dog();\n" +
                "      animal.makeSound(); // 输出：Dog barks\n" +
                "      ```\n" +
                "   4. 作用：提高代码的灵活性、可扩展性和可维护性。");

        // 构建用户消息
        ChatMessage userMessage = new ChatMessage();
        userMessage.setRole("user");
        userMessage.setContent(question);

        // 发送请求
        return getChatCompletion(List.of(systemMessage, userMessage), "gpt");
    }
} 