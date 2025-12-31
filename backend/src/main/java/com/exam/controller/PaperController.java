package com.exam.controller;

import com.exam.common.Result;
import com.exam.dto.AiPaperDto;
import com.exam.dto.PaperDto;
import com.exam.entity.Paper;
import com.exam.service.PaperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.StringUtils;

/**
 * 试卷控制器 - 处理试卷管理相关的HTTP请求
 * 包括试卷的CRUD操作、AI智能组卷、状态管理等功能
 */
@RestController  // REST控制器，返回JSON数据
@RequestMapping("/api/papers")  // 试卷API路径前缀
@Tag(name = "试卷管理", description = "试卷相关操作，包括创建、查询、更新、删除，以及AI智能组卷功能")  // Swagger API分组
public class PaperController {

    /**
     * 注入试卷业务服务
     */
    @Autowired
    private PaperService paperService;

    /**
     * 获取所有试卷列表（支持模糊搜索和状态筛选）
     */
    @GetMapping("/list")  // 处理GET请求
    @Operation(summary = "获取试卷列表", description = "支持按名称模糊搜索和状态筛选的试卷列表查询")  // API描述
    public Result<java.util.List<Paper>> listPapers(
            @Parameter(description = "试卷名称，支持模糊查询") @RequestParam(required = false) String name,
            @Parameter(description = "试卷状态，可选值：DRAFT/PUBLISHED/STOPPED") @RequestParam(required = false) String status) {
        
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();

        // 如果name不为空，则按名称进行模糊查询
        if (StringUtils.hasText(name)) {
            queryWrapper.like("name", name);
        }

        // 如果status不为空，则按状态查询
        if (StringUtils.hasText(status)) {
            queryWrapper.eq("status", status);
        }

        // 默认按创建时间倒序排序
        queryWrapper.orderByDesc("create_time");
        
        return Result.success(paperService.list(queryWrapper));
    }

    /**
     * 手动创建试卷
     */
    @PostMapping  // 处理POST请求
    @Operation(summary = "手动创建试卷", description = "通过手动选择题目的方式创建试卷")  // API描述
    public Result<Paper> createPaper(@RequestBody PaperDto paperDto) {
        Paper paper = paperService.createPaper(paperDto);
        return Result.success(paper, "试卷创建成功");
    }

    /**
     * 更新试卷
     * @param id 试卷ID
     * @param paperDto 试卷更新数据
     * @return 操作结果
     */
    @PutMapping("/{id}")  // 处理PUT请求
    @Operation(summary = "更新试卷信息", description = "更新试卷的基本信息和题目配置")  // API描述
    public Result<Paper> updatePaper(
            @Parameter(description = "试卷ID") @PathVariable Integer id, 
            @RequestBody PaperDto paperDto) {
        Paper updatedPaper = paperService.updatePaper(id, paperDto);
        return Result.success(updatedPaper, "试卷更新成功");
    }

    /**
     * AI智能组卷（新版）
     * @param aiPaperDto 包含试卷信息和组卷规则的数据
     * @return 创建好的试卷
     */
    @PostMapping("/ai")  // 处理POST请求
    @Operation(summary = "AI智能组卷", description = "基于设定的规则（题型分布、难度配比等）使用AI自动生成试卷")  // API描述
    public Result<Paper> createPaperWithAI(@RequestBody AiPaperDto aiPaperDto) {
        Paper paper = paperService.createPaperWithAI(aiPaperDto);
        return Result.success(paper, "AI智能组卷成功");
    }

    /**
     * 获取试卷详情（包含题目）
     */
    @GetMapping("/{id}")  // 处理GET请求
    @Operation(summary = "获取试卷详情", description = "获取试卷的详细信息，包括试卷基本信息和包含的所有题目")  // API描述
    public Result<Paper> getPaperById(@Parameter(description = "试卷ID") @PathVariable Long id) {
        Paper paper = paperService.getPaperWithQuestions(id);
        return Result.success(paper);
    }

    /**
     * 更新试卷状态（发布/停止）
     * @param id 试卷ID
     * @param status 新的状态
     * @return 操作结果
     */
    @PostMapping("/{id}/status")  // 处理POST请求
    @Operation(summary = "更新试卷状态", description = "修改试卷状态：发布试卷供学生考试或停止试卷禁止考试")  // API描述
    public Result<Void> updatePaperStatus(
            @Parameter(description = "试卷ID") @PathVariable Integer id, 
            @Parameter(description = "新的状态，可选值：PUBLISHED/STOPPED") @RequestParam String status) {
        paperService.updatePaperStatus(id, status);
        return Result.success(null, "状态更新成功");
    }

    /**
     * 删除试卷
     * @param id 试卷ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")  // 处理DELETE请求
    @Operation(summary = "删除试卷", description = "删除指定的试卷，注意：已发布的试卷不能删除")  // API描述
    public Result<Void> deletePaper(@Parameter(description = "试卷ID") @PathVariable Integer id) {
        // 检查试卷是否存在  // 验证试卷存在性
        Paper paper = paperService.getById(id);
        if (paper == null) {
            return Result.error("试卷不存在");
        }
        
        // 检查试卷是否可以删除（例如：草稿状态才能删除）  // 检查删除权限
        if ("PUBLISHED".equals(paper.getStatus())) {
            return Result.error("已发布的试卷不能删除，请先停止发布");
        }
        
        // 执行删除操作  // 删除试卷
        boolean deleted = paperService.removeById(id);
        if (deleted) {
            return Result.success(null, "试卷删除成功");
        } else {
            return Result.error("试卷删除失败");
        }
    }
} 