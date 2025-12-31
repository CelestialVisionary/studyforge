package com.exam.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.dto.ai.ChatMessage;
import com.exam.dto.ai.ChatRequest;
import com.exam.entity.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Kimi AI判卷服务
 * 使用Kimi API进行智能批改和点评
 */
@Service
@Slf4j
public class KimiGradingService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${kimi.api.base-url}")
    private String baseUrl;

    @Value("${kimi.api.api-key}")
    private String apiKey;

    @Value("${kimi.api.model}")
    private String model;

    @Value("${kimi.api.max-tokens}")
    private Integer maxTokens;

    @Value("${kimi.api.temperature}")
    private Double temperature;

    /**
     * 智能判卷单道题目
     * @param question 题目信息
     * @param userAnswer 用户答案
     * @param maxScore 题目满分
     * @return 判卷结果，包含得分、评语等
     */
    public GradingResult gradeQuestion(Question question, String userAnswer, Integer maxScore) {
        try {
            String prompt = buildGradingPrompt(question, userAnswer, maxScore);
            String response = callKimiAPI(prompt);
            return parseGradingResponse(response, maxScore);
        } catch (Exception e) {
            log.error("AI判卷失败，题目ID: {}, 错误: {}", question.getId(), e.getMessage());
            // 返回默认评分结果
            return new GradingResult(0, "AI判卷服务暂时不可用，请手动批阅。", "系统错误");
        }
    }

    /**
     * 生成考试总评和建议
     * @param totalScore 总得分
     * @param maxScore 总满分
     * @param questionCount 题目总数
     * @param correctCount 答对题目数
     * @return 考试总评
     */
    public String generateExamSummary(Integer totalScore, Integer maxScore, Integer questionCount, Integer correctCount) {
        try {
            String prompt = buildSummaryPrompt(totalScore, maxScore, questionCount, correctCount);
            return callKimiAPI(prompt);
        } catch (Exception e) {
            log.error("生成考试总评失败: {}", e.getMessage());
            // 返回默认总评
            double percentage = (double) totalScore / maxScore * 100;
            return String.format("本次考试总分 %d/%d 分（%.1f%%）。建议多加练习，持续提升！", 
                    totalScore, maxScore, percentage);
        }
    }

    /**
     * 构建判卷提示词
     */
    private String buildGradingPrompt(Question question, String userAnswer, Integer maxScore) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("你是一名专业的考试阅卷老师，请对以下题目进行判卷：\n\n");
        
        prompt.append("【题目信息】\n");
        prompt.append("题型：").append(getQuestionTypeText(question.getType())).append("\n");
        prompt.append("题目：").append(question.getTitle()).append("\n");
        prompt.append("标准答案：").append(question.getAnswer().getAnswer()).append("\n");
        prompt.append("满分：").append(maxScore).append("分\n\n");
        
        prompt.append("【学生答案】\n");
        prompt.append(userAnswer.trim().isEmpty() ? "（未作答）" : userAnswer).append("\n\n");
        
        prompt.append("【判卷要求】\n");
        if ("CHOICE".equals(question.getType()) || "JUDGE".equals(question.getType())) {
            prompt.append("- 客观题：答案完全正确得满分，答案错误得0分\n");
        } else if ("TEXT".equals(question.getType())) {
            prompt.append("- 主观题：根据答案的准确性、完整性、逻辑性进行评分\n");
            prompt.append("- 答案要点正确且完整：80-100%分数\n");
            prompt.append("- 答案基本正确但不够完整：60-80%分数\n");
            prompt.append("- 答案部分正确：30-60%分数\n");
            prompt.append("- 答案完全错误或未作答：0分\n");
        }
        
        prompt.append("\n请按以下JSON格式返回判卷结果：\n");
        prompt.append("{\n");
        prompt.append("  \"score\": 实际得分(整数),\n");
        prompt.append("  \"feedback\": \"具体的评价反馈(50字以内)\",\n");
        prompt.append("  \"reason\": \"扣分原因或得分依据(30字以内)\"\n");
        prompt.append("}");
        
        return prompt.toString();
    }

    /**
     * 构建考试总评提示词
     */
    private String buildSummaryPrompt(Integer totalScore, Integer maxScore, Integer questionCount, Integer correctCount) {
        double percentage = (double) totalScore / maxScore * 100;
        
        StringBuilder prompt = new StringBuilder();
        prompt.append("你是一名资深的教育专家，请为学生的考试表现提供专业的总评和学习建议：\n\n");
        
        prompt.append("【考试成绩】\n");
        prompt.append("总得分：").append(totalScore).append("/").append(maxScore).append("分\n");
        prompt.append("得分率：").append(String.format("%.1f", percentage)).append("%\n");
        prompt.append("题目总数：").append(questionCount).append("道\n");
        prompt.append("答对题数：").append(correctCount).append("道\n\n");
        
        prompt.append("【要求】\n");
        prompt.append("请提供一份150字左右的考试总评，包括：\n");
        prompt.append("1. 对本次考试表现的客观评价\n");
        prompt.append("2. 指出优势和不足之处\n");
        prompt.append("3. 提供具体的学习建议和改进方向\n");
        prompt.append("4. 给予鼓励和激励\n\n");
        
        prompt.append("请直接返回总评内容，无需特殊格式：");
        
        return prompt.toString();
    }

    /**
     * 调用Kimi API
     */
    private String callKimiAPI(String prompt) {
        String url = baseUrl + "/chat/completions";
        
        // 构建请求
        ChatRequest request = ChatRequest.builder()
                .model(model)
                .messages(Arrays.asList(new ChatMessage("user", prompt)))
                .temperature(temperature)
                .maxTokens(maxTokens)
                .build();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(request), headers);

        // 发送请求
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        
        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonResponse = JSON.parseObject(response.getBody());
            JSONArray choices = jsonResponse.getJSONArray("choices");
            if (choices != null && choices.size() > 0) {
                JSONObject choice = choices.getJSONObject(0);
                JSONObject message = choice.getJSONObject("message");
                return message.getString("content");
            }
        }
        
        throw new RuntimeException("Kimi API调用失败");
    }

    /**
     * 解析判卷响应
     */
    private GradingResult parseGradingResponse(String response, Integer maxScore) {
        try {
            // 尝试解析JSON格式的响应
            JSONObject json = JSON.parseObject(response);
            Integer score = json.getInteger("score");
            String feedback = json.getString("feedback");
            String reason = json.getString("reason");
            
            // 分数范围检查
            if (score == null || score < 0) score = 0;
            if (score > maxScore) score = maxScore;
            
            return new GradingResult(score, feedback, reason);
        } catch (Exception e) {
            log.warn("解析AI响应失败，使用默认解析: {}", e.getMessage());
            // 如果JSON解析失败，尝试从文本中提取信息
            return parseTextResponse(response, maxScore);
        }
    }

    /**
     * 从文本响应中解析结果
     */
    private GradingResult parseTextResponse(String response, Integer maxScore) {
        // 简单的文本解析逻辑
        Integer score = 0;
        String feedback = "AI判卷完成";
        String reason = "系统自动评分";
        
        // 尝试从响应中提取分数
        String[] lines = response.split("\n");
        for (String line : lines) {
            if (line.contains("分") && line.matches(".*\\d+.*")) {
                try {
                    String numberStr = line.replaceAll("[^0-9]", "");
                    if (!numberStr.isEmpty()) {
                        Integer extractedScore = Integer.parseInt(numberStr);
                        if (extractedScore <= maxScore) {
                            score = extractedScore;
                        }
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }
        
        // 截取响应的前100个字符作为反馈
        if (response.length() > 100) {
            feedback = response.substring(0, 100) + "...";
        } else {
            feedback = response;
        }
        
        return new GradingResult(score, feedback, reason);
    }

    /**
     * 获取题目类型文本
     */
    private String getQuestionTypeText(String type) {
        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("CHOICE", "选择题");
        typeMap.put("JUDGE", "判断题");
        typeMap.put("TEXT", "简答题");
        return typeMap.getOrDefault(type, "未知题型");
    }

    /**
     * 判卷结果内部类
     */
    public static class GradingResult {
        private Integer score;
        private String feedback;
        private String reason;

        public GradingResult(Integer score, String feedback, String reason) {
            this.score = score;
            this.feedback = feedback;
            this.reason = reason;
        }

        // Getters
        public Integer getScore() { return score; }
        public String getFeedback() { return feedback; }
        public String getReason() { return reason; }
    }
} 