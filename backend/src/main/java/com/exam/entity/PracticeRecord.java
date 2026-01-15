package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 练习记录实体类 - 存储用户的练习历史
 */
@TableName(value = "practice_record")
@Data
@NoArgsConstructor
@Schema(description = "练习记录信息")
public class PracticeRecord implements Serializable {
    
    @Schema(description = "练习记录ID，唯一标识", 
            example = "1")
    @TableId(type = IdType.AUTO)
    private Long id; // 主键ID

    @Schema(description = "用户ID", 
            example = "1")
    private Long userId; // 用户ID

    @Schema(description = "练习类型", 
            example = "KNOWLEDGE_POINT", 
            allowableValues = {"KNOWLEDGE_POINT", "CATEGORY", "RANDOM", "HOT"})
    private String practiceType; // 练习类型：知识点练习、分类练习、随机练习、热门练习

    @Schema(description = "关联ID", 
            example = "2")
    private Long relationId; // 关联ID：知识点ID、分类ID等

    @Schema(description = "练习题目数量", 
            example = "10")
    private Integer questionCount; // 练习题目数量

    @Schema(description = "正确题目数量", 
            example = "8")
    private Integer correctCount; // 正确题目数量

    @Schema(description = "错误题目数量", 
            example = "2")
    private Integer wrongCount; // 错误题目数量

    @Schema(description = "平均得分", 
            example = "85")
    private Integer averageScore; // 平均得分

    @Schema(description = "练习用时（秒）", 
            example = "300")
    private Integer duration; // 练习用时（秒）

    @Schema(description = "练习时间", 
            example = "2023-01-01 10:00:00")
    private Date practiceTime; // 练习时间

    @TableField(exist = false)
    private static final long serialVersionUID = 1L; // 序列化版本UID
}