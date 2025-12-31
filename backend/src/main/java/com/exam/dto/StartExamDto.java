package com.exam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 开始考试请求DTO - 学生开始考试时提交的参数
 */
@Data
@Schema(description = "开始考试请求参数")
public class StartExamDto {
    
    @Schema(description = "试卷ID，指定要参加的考试试卷", 
            example = "1", 
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "试卷ID不能为空")
    private Integer paperId; // 试卷ID
    
    @Schema(description = "考生姓名", 
            example = "张三", 
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "考生姓名不能为空")
    private String studentName; // 考生姓名
} 