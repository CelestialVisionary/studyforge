package com.exam.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 * 所有业务实体类的父类，提供通用字段和功能
 * 
 * @author 智能学习平台开发团队
 * @version 1.0
 */
@Data
public class BaseEntity implements Serializable {

    @Schema(description="主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Schema(description="创建时间")
    private Date createTime;

    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JsonIgnore
    @Schema(description="修改时间")
    private Date updateTime;

    @JsonIgnore
    @Schema(description="逻辑删除")
    @TableField("is_deleted")
    @TableLogic
    private Byte isDeleted;
}