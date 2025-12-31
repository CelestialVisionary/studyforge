package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.dto.AiPaperDto;
import com.exam.dto.PaperDto;
import com.exam.entity.Paper;

/**
 * 试卷服务接口
 */
public interface PaperService extends IService<Paper> {

    /**
     * 创建试卷（手动组卷）
     * @param paperDto 试卷数据
     * @return 创建好的试卷
     */
    Paper createPaper(PaperDto paperDto);

    /**
     * 更新试卷
     * @param paperId 试卷ID
     * @param paperDto 试卷更新数据
     * @return 更新后的试卷
     */
    Paper updatePaper(Integer paperId, PaperDto paperDto);

    /**
     * 智能创建试卷（AI组卷）
     * @param categoryId 题目分类ID
     * @param questionCount 题目数量
     * @param totalScore 总分
     * @return 创建好的试卷
     */
    Paper createPaperWithAI(Integer categoryId, Integer questionCount, Integer totalScore);

    /**
     * 智能创建试卷（AI组卷）
     * @param aiPaperDto AI试卷数据
     * @return 创建好的试卷
     */
    Paper createPaperWithAI(AiPaperDto aiPaperDto);

    /**
     * 获取带题目的试卷详情
     * @param paperId 试卷ID
     * @return 试卷详情
     */
    Paper getPaperWithQuestions(Long paperId);

    /**
     * 更新试卷状态
     * @param paperId 试卷ID
     * @param status 新的状态
     */
    void updatePaperStatus(Integer paperId, String status);
} 