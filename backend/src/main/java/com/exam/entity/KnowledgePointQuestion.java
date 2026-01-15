package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 知识点与题目的关联实体类 - 存储知识点与题目的多对多关系
 */
@TableName(value = "knowledge_point_question")
@Data
@NoArgsConstructor
@Schema(description = "知识点与题目的关联信息")
public class KnowledgePointQuestion implements Serializable {
    
    @Schema(description = "关联ID，唯一标识", 
            example = "1")
    @TableId(type = IdType.AUTO)
    private Long id; // 主键ID

    @Schema(description = "知识点ID", 
            example = "1")
    private Long knowledgePointId; // 知识点ID

    @Schema(description = "题目ID", 
            example = "5")
    private Long questionId; // 题目ID

    @TableField(exist = false)
    private static final long serialVersionUID = 1L; // 序列化版本UID

    public KnowledgePointQuestion(Long knowledgePointId, Long questionId) {
        this.knowledgePointId = knowledgePointId;
        this.questionId = questionId;
    }
}