package com.exam.service;

import com.exam.dto.StatsDto;

/**
 * 统计数据服务接口
 */
public interface StatsService {
    
    /**
     * 获取系统统计数据
     * @return 统计数据DTO
     */
    StatsDto getSystemStats();
} 