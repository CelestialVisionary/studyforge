package com.exam.controller;

import com.exam.common.Result;
import com.exam.dto.AiGenerateRequestDto;
import com.exam.dto.QuestionImportDto;
import com.exam.service.KimiAiService;
import com.exam.service.QuestionService;
import com.exam.utils.ExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 题目批量管理控制器 - 处理题目批量操作相关的HTTP请求
 * 包括Excel导入、AI生成题目、批量验证等功能
 */
@Slf4j  // 日志注解
@RestController  // REST控制器，返回JSON数据
@RequestMapping("/api/questions/batch")  // 题目批量操作API路径前缀
@CrossOrigin(origins = "*")  // 允许跨域访问
@Tag(name = "题目批量操作", description = "题目批量管理相关操作，包括Excel导入、AI生成题目、批量验证等功能")  // Swagger API分组
public class QuestionBatchController {
    
    /**
     * 注入题目业务服务
     */
    @Autowired
    private QuestionService questionService; // 注入题目服务
    
    /**
     * 注入AI服务
     */
    @Autowired
    private KimiAiService kimiAiService; // 注入Kimi AI服务
    
    /**
     * 下载Excel导入模板
     * @return Excel模板文件
     */
    @GetMapping("/template")  // 处理GET请求
    @Operation(summary = "下载Excel导入模板", description = "下载题目批量导入的Excel模板文件")  // API描述
    public ResponseEntity<byte[]> downloadTemplate() {
        try {
            byte[] template = ExcelUtil.generateTemplate();
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=question_import_template.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(template);
                    
        } catch (Exception e) {
            log.error("生成Excel模板失败", e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * 预览Excel文件内容（不入库）
     * @param file Excel文件
     * @return 解析出的题目列表
     */
    @PostMapping("/preview-excel")  // 处理POST请求
    @Operation(summary = "预览Excel文件内容", description = "解析并预览Excel文件中的题目内容，不会导入到数据库")  // API描述
    public Result<List<QuestionImportDto>> previewExcel(
            @Parameter(description = "Excel文件，支持.xls和.xlsx格式") @RequestParam("file") MultipartFile file) {
        try {
            // 验证文件格式
            if (file.isEmpty()) {
                return Result.error("文件不能为空");
            }
            
            String fileName = file.getOriginalFilename();
            if (fileName == null || (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls"))) {
                return Result.error("请上传Excel文件（.xlsx或.xls格式）");
            }
            
            // 解析Excel文件
            List<QuestionImportDto> questions = ExcelUtil.parseExcel(file);
            
            if (questions.isEmpty()) {
                return Result.error("Excel文件中没有有效的题目数据");
            }
            
            log.info("成功解析Excel文件，共{}道题目", questions.size());
            return Result.success(questions);
            
        } catch (Exception e) {
            log.error("解析Excel文件失败", e);
            return Result.error("解析Excel文件失败: " + e.getMessage());
        }
    }
    
    /**
     * 从Excel文件批量导入题目
     * @param file Excel文件
     * @return 导入结果
     */
    @PostMapping("/import-excel")  // 处理POST请求
    @Operation(summary = "从Excel文件批量导入题目", description = "解析Excel文件并将题目批量导入到数据库")  // API描述
    public Result<String> importFromExcel(
            @Parameter(description = "Excel文件，包含题目数据") @RequestParam("file") MultipartFile file) {
        try {
            // 验证文件格式
            if (file.isEmpty()) {
                return Result.error("文件不能为空");
            }
            
            String fileName = file.getOriginalFilename();
            if (fileName == null || (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls"))) {
                return Result.error("请上传Excel文件（.xlsx或.xls格式）");
            }
            
            // 解析Excel文件
            List<QuestionImportDto> questions = ExcelUtil.parseExcel(file);
            
            if (questions.isEmpty()) {
                return Result.error("Excel文件中没有有效的题目数据");
            }
            
            // 批量导入题目
            int successCount = questionService.batchImportQuestions(questions);
            
            String message = String.format("Excel导入完成！成功导入 %d / %d 道题目", successCount, questions.size());
            log.info(message);
            
            return Result.success(message);
            
        } catch (Exception e) {
            log.error("Excel批量导入失败", e);
            return Result.error("Excel批量导入失败: " + e.getMessage());
        }
    }
    
    /**
     * 使用AI生成题目（预览，不入库）
     * @param request AI生成请求参数
     * @return 生成的题目列表
     */
    @PostMapping("/ai-generate")  // 处理POST请求
    @Operation(summary = "AI智能生成题目", description = "使用AI技术根据指定主题和要求智能生成题目，支持预览后再决定是否导入")  // API描述
    public Result<List<QuestionImportDto>> generateQuestionsByAi(
            @RequestBody @Validated AiGenerateRequestDto request) {
        try {
            // 调用AI服务生成题目
            List<QuestionImportDto> questions = kimiAiService.generateQuestions(request);
            
            if (questions.isEmpty()) {
                return Result.error("AI未能生成题目，请检查参数或稍后重试");
            }
            
            log.info("AI成功生成{}道关于【{}】的题目", questions.size(), request.getTopic());
            return Result.success(questions);
            
        } catch (Exception e) {
            log.error("AI生成题目失败", e);
            return Result.error("AI生成题目失败: " + e.getMessage());
        }
    }
    
    /**
     * 批量导入题目（通用接口，支持Excel导入或AI生成后的确认导入）
     * @param questions 题目导入DTO列表
     * @return 导入结果
     */
    @PostMapping("/import-questions")  // 处理POST请求
    @Operation(summary = "批量导入题目", description = "将题目列表批量导入到数据库，支持Excel解析后的导入或AI生成后的确认导入")  // API描述
    public Result<String> importQuestions(@RequestBody List<QuestionImportDto> questions) {
        try {
            if (questions == null || questions.isEmpty()) {
                return Result.error("题目列表不能为空");
            }
            
            // 批量导入题目
            int successCount = questionService.batchImportQuestions(questions);
            
            String message = String.format("批量导入完成！成功导入 %d / %d 道题目", successCount, questions.size());
            log.info(message);
            
            return Result.success(message);
            
        } catch (Exception e) {
            log.error("批量导入题目失败", e);
            return Result.error("批量导入题目失败: " + e.getMessage());
        }
    }
    
    /**
     * 验证题目数据
     * @param questions 题目列表
     * @return 验证结果
     */
    @PostMapping("/validate")  // 处理POST请求
    @Operation(summary = "验证题目数据", description = "验证题目数据的完整性和格式正确性，返回验证结果和错误信息")  // API描述
    public Result<String> validateQuestions(@RequestBody List<QuestionImportDto> questions) {
        try {
            if (questions == null || questions.isEmpty()) {
                return Result.error("题目列表不能为空");
            }
            
            int validCount = 0;
            StringBuilder errors = new StringBuilder();
            
            for (int i = 0; i < questions.size(); i++) {
                QuestionImportDto question = questions.get(i);
                String error = validateSingleQuestion(question, i + 1);
                if (error == null) {
                    validCount++;
                } else {
                    errors.append(error).append("\n");
                }
            }
            
            if (validCount == questions.size()) {
                return Result.success("所有题目数据验证通过");
            } else {
                return Result.error("存在无效题目数据：\n" + errors.toString());
            }
            
        } catch (Exception e) {
            log.error("验证题目数据失败", e);
            return Result.error("验证题目数据失败: " + e.getMessage());
        }
    }
    
    /**
     * 验证单个题目数据
     * @param question 题目数据
     * @param index 题目序号
     * @return 错误信息，如果为null表示验证通过
     */
    private String validateSingleQuestion(QuestionImportDto question, int index) {
        // 验证基本字段
        if (question.getTitle() == null || question.getTitle().trim().isEmpty()) {
            return String.format("第%d题：题目内容不能为空", index);
        }
        
        if (question.getType() == null || question.getType().trim().isEmpty()) {
            return String.format("第%d题：题目类型不能为空", index);
        }
        
        if (!"CHOICE".equals(question.getType()) && !"JUDGE".equals(question.getType()) && !"TEXT".equals(question.getType())) {
            return String.format("第%d题：题目类型必须是CHOICE、JUDGE或TEXT", index);
        }
        
        // 验证选择题特有字段
        if ("CHOICE".equals(question.getType())) {
            if (question.getChoices() == null || question.getChoices().isEmpty()) {
                return String.format("第%d题：选择题必须有选项", index);
            }
            
            if (question.getChoices().size() < 2) {
                return String.format("第%d题：选择题至少需要2个选项", index);
            }
            
            boolean hasCorrectAnswer = question.getChoices().stream()
                    .anyMatch(choice -> choice.getIsCorrect() != null && choice.getIsCorrect());
            
            if (!hasCorrectAnswer) {
                return String.format("第%d题：选择题必须有正确答案", index);
            }
        } else {
            // 判断题和简答题需要答案
            if (question.getAnswer() == null || question.getAnswer().trim().isEmpty()) {
                return String.format("第%d题：%s必须有答案", index, 
                    "JUDGE".equals(question.getType()) ? "判断题" : "简答题");
            }
        }
        
        return null; // 验证通过
    }
} 