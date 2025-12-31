package com.exam.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 真题分类实体类，对应question_categories表
 */
@Data
public class QuestionCategory {
    /** 分类ID */
    private Integer id;
    /** 分类名称 */
    private String name;
    /** 父分类ID，0表示顶级分类 */
    private Integer parentId;
    /** 分类层级：1-一级分类 2-二级分类 */
    private Integer level;
    /** 排序序号 */
    private Integer sortOrder;
    /** 分类描述 */
    private String description;
    /** 状态：0-禁用 1-启用 */
    private Integer status;
    /** 创建时间 */
    private Date createdTime;
    /** 更新时间 */
    private Date updatedTime;
    
    /** 子分类列表（用于树形结构展示，不对应数据库字段） */
    private List<QuestionCategory> children;
} 