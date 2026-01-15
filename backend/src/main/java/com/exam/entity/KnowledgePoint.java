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
 * 知识点实体类 - 存储知识点的基本信息
 */
@TableName(value = "knowledge_point")
@Data
@NoArgsConstructor
@Schema(description = "知识点信息")
public class KnowledgePoint implements Serializable {
    
    @Schema(description = "知识点ID，唯一标识", 
            example = "1")
    @TableId(type = IdType.AUTO)
    private Long id; // 主键ID

    @Schema(description = "知识点名称", 
            example = "Java集合框架")
    private String name; // 知识点名称

    @Schema(description = "知识点描述", 
            example = "Java集合框架是Java中用于存储和操作对象的容器类的集合")
    private String description; // 知识点描述

    @Schema(description = "所属分类ID", 
            example = "2")
    private Long categoryId; // 所属分类ID

    @Schema(description = "知识点热度", 
            example = "100")
    private Integer hot; // 知识点热度

    @Schema(description = "创建时间", 
            example = "2023-01-01 10:00:00")
    private Date createTime; // 创建时间

    @Schema(description = "更新时间", 
            example = "2023-01-01 10:00:00")
    private Date updateTime; // 更新时间

    @TableField(exist = false)
    private static final long serialVersionUID = 1L; // 序列化版本UID
}