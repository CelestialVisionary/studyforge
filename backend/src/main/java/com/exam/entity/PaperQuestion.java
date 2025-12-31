package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 试卷-题目关联表
 * @TableName paper_question
 */
@TableName(value ="paper_question")
@Data
@NoArgsConstructor
public class PaperQuestion implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id; // 主键ID

    /**
     * 试卷ID
     */
    private Integer paperId; // 试卷ID

    /**
     * 题目ID
     */
    private Long questionId; // 题目ID

    /**
     * 题目分数
     */
    private BigDecimal score; // 题目分数

    private static final long serialVersionUID = 1L; // 序列化版本UID

    /**
     * 构造函数
     * @param paperId 试卷ID
     * @param questionId 题目ID
     * @param score 分数
     */
    public PaperQuestion(Integer paperId, Long questionId, BigDecimal score) {
        this.paperId = paperId;
        this.questionId = questionId;
        this.score = score;
    }
} 