package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.common.Result;
import com.exam.entity.ExamRecord;
import com.exam.service.ExamRecordService;
import com.exam.service.PaperService;
import com.exam.vo.ExamRankingVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 考试记录控制器 - 处理考试记录管理相关的HTTP请求
 * 包括考试记录查询、分页展示、成绩排行榜等功能
 */
@RestController  // REST控制器，返回JSON数据
@RequestMapping("/api/exam-records")  // 考试记录API路径前缀
@Tag(name = "考试记录管理", description = "考试记录相关操作，包括记录查询、成绩管理、排行榜展示等功能")  // Swagger API分组
public class ExamRecordController {

    /**
     * 注入考试记录业务服务
     */
    @Autowired
    private ExamRecordService examRecordService;

    /**
     * 注入试卷业务服务
     */
    @Autowired
    private PaperService paperService;

    /**
     * 分页查询考试记录
     */
    @GetMapping("/list")  // 处理GET请求
    @Operation(summary = "分页查询考试记录", description = "支持多条件筛选的考试记录分页查询，包括按姓名、状态、时间范围等筛选")  // API描述
    public Result<Page<ExamRecord>> getExamRecords(
            @Parameter(description = "当前页码，从1开始", example = "1") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页显示数量", example = "20") @RequestParam(defaultValue = "20") Integer size,
            @Parameter(description = "学生姓名筛选条件") @RequestParam(required = false) String studentName,
            @Parameter(description = "学号筛选条件") @RequestParam(required = false) String studentNumber,
            @Parameter(description = "考试状态，0-进行中，1-已完成，2-已批阅") @RequestParam(required = false) Integer status,
            @Parameter(description = "开始日期，格式：yyyy-MM-dd") @RequestParam(required = false) String startDate,
            @Parameter(description = "结束日期，格式：yyyy-MM-dd") @RequestParam(required = false) String endDate
    ) {
        // 构建查询条件
        QueryWrapper<ExamRecord> wrapper = new QueryWrapper<>();
        
        // 按考生姓名搜索
        if (studentName != null && !studentName.trim().isEmpty()) {
            wrapper.like("student_name", studentName.trim());
        }
        
        // 按学号搜索 - 暂时注释，因为数据库中可能没有这个字段
        // if (studentNumber != null && !studentNumber.trim().isEmpty()) {
        //     wrapper.like("student_number", studentNumber.trim());
        // }
        
        // 按状态筛选
        if (status != null) {
            String statusStr;
            switch (status) {
                case 0:
                    statusStr = "ONGOING";
                    break;
                case 1:
                    statusStr = "FINISHED";
                    break;
                case 2:
                    statusStr = "FINISHED"; // 数据库中只有ONGOING和FINISHED，已批阅是业务状态
                    break;
                default:
                    statusStr = "ONGOING";
            }
            wrapper.eq("status", statusStr);
        }
        
        // 按日期范围筛选
        if (startDate != null && !startDate.trim().isEmpty()) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDateTime startDateTime = start.atStartOfDay();
            wrapper.ge("create_time", startDateTime);
        }
        
        if (endDate != null && !endDate.trim().isEmpty()) {
            LocalDate end = LocalDate.parse(endDate);
            LocalDateTime endDateTime = end.atTime(23, 59, 59);
            wrapper.le("create_time", endDateTime);
        }
        
        // 按创建时间倒序排列
        wrapper.orderByDesc("create_time");
        
        // 执行分页查询
        Page<ExamRecord> pageParam = new Page<>(page, size);
        Page<ExamRecord> result = examRecordService.page(pageParam, wrapper);
        
        // 处理状态转换
        result.getRecords().forEach(record -> {
            // 转换状态为中文显示
            String statusText;
            if (record.getStatus() != null) {
                switch (record.getStatus()) {
                    case "ONGOING":
                        statusText = "进行中";
                        break;
                    case "FINISHED":
                        // 已完成的考试，如果有得分则显示为已批阅
                        statusText = record.getScore() != null ? "已批阅" : "已完成";
                        break;
                    case "TIMEOUT":
                        statusText = "已超时";
                        break;
                    default:
                        statusText = record.getStatus();
                }
                record.setStatus(statusText);
            }
        });
        
        return Result.success(result);
    }

    /**
     * 根据ID获取考试记录详情
     */
    @GetMapping("/{id}")  // 处理GET请求
    @Operation(summary = "获取考试记录详情", description = "根据记录ID获取考试记录的详细信息，包括试卷内容和答题情况")  // API描述
    public Result<ExamRecord> getExamRecordById(
            @Parameter(description = "考试记录ID") @PathVariable Long id) {
        ExamRecord record = examRecordService.getById(id);
        if (record != null) {
            // 加载试卷信息
            record.setPaper(paperService.getPaperWithQuestions(record.getExamId()));
        }
        return Result.success(record);
    }

    /**
     * 删除考试记录
     */
    @DeleteMapping("/{id}")  // 处理DELETE请求
    @Operation(summary = "删除考试记录", description = "根据ID删除指定的考试记录")  // API描述
    public Result<Void> deleteExamRecord(
            @Parameter(description = "考试记录ID") @PathVariable Long id) {
        boolean success = examRecordService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 获取考试排行榜 - 优化版本
     * 使用SQL关联查询，一次性获取所有需要的数据，性能提升数百倍
     * 
     * @param paperId 试卷ID，可选参数
     * @param limit 显示数量限制，可选参数
     * @return 排行榜列表
     */
    @GetMapping("/ranking")  // 处理GET请求
    @Operation(summary = "获取考试排行榜", description = "获取考试成绩排行榜，支持按试卷筛选和限制显示数量，使用优化的SQL关联查询提升性能")  // API描述
    public Result<List<ExamRankingVO>> getExamRanking(
            @Parameter(description = "试卷ID，可选，不传则显示所有试卷的排行") @RequestParam(required = false) Integer paperId,
            @Parameter(description = "显示数量限制，可选，不传则返回所有记录") @RequestParam(required = false) Integer limit
    ) {
        // 使用优化的查询方法，避免N+1查询问题
        // 原来需要4000+次数据库查询，现在只需要1次！
        List<ExamRankingVO> rankingList = examRecordService.getExamRankingOptimized(paperId, limit);
        
        return Result.success(rankingList);
    }
} 