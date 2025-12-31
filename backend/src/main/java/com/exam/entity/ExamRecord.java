package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 考试记录表 - 存储学生的考试过程和结果数据
 */
@TableName("exam_records")
@Data
@Schema(description = "考试记录信息")
public class ExamRecord implements Serializable {
    
    @Schema(description = "考试记录ID，唯一标识", 
            example = "1")
    @TableId(type = IdType.AUTO)
    private Long id; // 主键ID

    @Schema(description = "试卷ID，关联的考试试卷", 
            example = "1")
    @TableField(value = "exam_id", exist = true)
    private Long examId; // 试卷ID

    @Schema(description = "考生姓名", 
            example = "张三")
    @TableField(value = "student_name", exist = true)
    private String studentName; // 考生姓名
    
    @Schema(description = "考生学号/工号", 
            example = "2024001")
    @TableField(value = "student_number", exist = false)
    private String studentNumber; // 考生学号/工号

    @Schema(description = "考试得分", 
            example = "85")
    @TableField(value = "score", exist = true)
    private Integer score; // 得分

    @Schema(description = "答题记录，JSON格式存储所有答题内容", 
            example = "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]")
    @TableField(value = "answers", exist = true)
    private String answers; // 答题记录

    @Schema(description = "考试开始时间", 
            example = "2024-01-15 09:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "start_time", exist = true)
    private LocalDateTime startTime; // 开始时间

    @Schema(description = "考试结束时间", 
            example = "2024-01-15 11:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "end_time", exist = true)
    private LocalDateTime endTime; // 结束时间

    @Schema(description = "考试状态", 
            example = "ONGOING", 
            allowableValues = {"ONGOING", "FINISHED", "TIMEOUT"})
    @TableField(value = "status", exist = true)
    private String status; // 考试状态
    
    @Schema(description = "提交IP", 
            example = "127.0.0.1")
    @TableField(value = "submit_ip", exist = false)
    private String submitIp; // 提交IP

    @Schema(description = "窗口切换次数，用于监控考试过程中的异常行为", 
            example = "2")
    @TableField(value = "window_switches", exist = false)
    private Integer windowSwitches; // 窗口切换次数

    @Schema(description = "记录创建时间", 
            example = "2024-01-15 09:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time", exist = true)
    private LocalDateTime createTime; // 创建时间
    
    @Schema(description = "记录更新时间", 
            example = "2024-01-15 09:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_time", exist = true)
    private LocalDateTime updateTime; // 更新时间

    @Schema(description = "详细的答题记录列表，包含每题的答案和得分情况")
    @TableField(exist = false)
    private List<AnswerRecord> answerRecords; // 答案记录列表

    @Schema(description = "关联的试卷信息，包含试卷详细内容和题目")
    @TableField(exist = false)
    private Paper paper; // 试卷信息

    @TableField(exist = false)
    private static final long serialVersionUID = 1L; // 序列化版本UID
} 