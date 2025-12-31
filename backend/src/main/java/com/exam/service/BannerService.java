package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.entity.Banner;
import com.exam.common.Result;

import java.util.List;

/**
 * 轮播图服务接口
 */
public interface BannerService extends IService<Banner> {
    
    /**
     * 获取所有启用的轮播图
     * @return 轮播图列表
     */
    Result<List<Banner>> getActiveBanners();
    
    /**
     * 获取所有轮播图（管理页面用）
     * @return 轮播图列表
     */
    Result<List<Banner>> getAllBanners();
    
    /**
     * 添加轮播图
     * @param banner 轮播图对象
     * @return 操作结果
     */
    Result<String> addBanner(Banner banner);
    
    /**
     * 更新轮播图
     * @param banner 轮播图对象
     * @return 操作结果
     */
    Result<String> updateBanner(Banner banner);
    
    /**
     * 删除轮播图
     * @param id 轮播图ID
     * @return 操作结果
     */
    Result<String> deleteBanner(Long id);
    
    /**
     * 启用/禁用轮播图
     * @param id 轮播图ID
     * @param isActive 是否启用
     * @return 操作结果
     */
    Result<String> toggleBannerStatus(Long id, Boolean isActive);
    
} 