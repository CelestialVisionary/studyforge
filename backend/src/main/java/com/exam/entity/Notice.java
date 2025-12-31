package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 公告实体类 - 系统公告信息
 */
@Data
@TableName("notices")
@Schema(description = "系统公告信息")
public class Notice {
    
    @Schema(description = "公告ID，唯一标识", 
            example = "1")
    @TableId(type = IdType.AUTO)
    private Long id; // 公告ID
    
    @Schema(description = "公告标题", 
            example = "系统维护通知")
    private String title; // 公告标题
    
    @Schema(description = "公告内容详情", 
            example = "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...")
    private String content; // 公告内容
    
    @Schema(description = "公告类型", 
            example = "SYSTEM", 
            allowableValues = {"SYSTEM", "FEATURE", "NOTICE"})
    private String type; // 公告类型：SYSTEM(系统)、FEATURE(新功能)、NOTICE(通知)
    
    @Schema(description = "优先级级别", 
            example = "1", 
            allowableValues = {"0", "1", "2"})
    private Integer priority; // 优先级：0-普通，1-重要，2-紧急
    
    @Schema(description = "是否启用显示", 
            example = "true")
    private Boolean isActive; // 是否启用
    
    @Schema(description = "公告创建时间", 
            example = "2024-01-15 10:00:00")
    private LocalDateTime createTime; // 创建时间
    
    @Schema(description = "公告更新时间", 
            example = "2024-01-15 10:00:00")
    private LocalDateTime updateTime; // 更新时间
    
    // 手动添加getter和setter方法
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getPriority() {
        return priority;
    }
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
} 