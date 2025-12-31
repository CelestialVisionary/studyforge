package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.common.Result;
import com.exam.entity.Banner;
import com.exam.mapper.BannerMapper;
import com.exam.service.BannerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 轮播图服务实现类
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
    
    @Override
    public Result<List<Banner>> getActiveBanners() {
        try {
            List<Banner> banners = baseMapper.selectActiveBanners();
            return Result.success(banners);
        } catch (Exception e) {
            return Result.error("获取轮播图失败：" + e.getMessage());
        }
    }
    
    @Override
    public Result<List<Banner>> getAllBanners() {
        try {
            QueryWrapper<Banner> wrapper = new QueryWrapper<>();
            wrapper.orderByAsc("sort_order");
            List<Banner> banners = this.list(wrapper);
            return Result.success(banners);
        } catch (Exception e) {
            return Result.error("获取轮播图列表失败：" + e.getMessage());
        }
    }
    
    @Override
    public Result<String> addBanner(Banner banner) {
        try {
            banner.setCreateTime(LocalDateTime.now());
            banner.setUpdateTime(LocalDateTime.now());
            if (banner.getIsActive() == null) {
                banner.setIsActive(true); // 默认启用
            }
            if (banner.getSortOrder() == null) {
                banner.setSortOrder(0); // 默认排序
            }
            
            boolean success = this.save(banner);
            if (success) {
                return Result.success("轮播图添加成功");
            } else {
                return Result.error("轮播图添加失败");
            }
        } catch (Exception e) {
            return Result.error("轮播图添加失败：" + e.getMessage());
        }
    }
    
    @Override
    public Result<String> updateBanner(Banner banner) {
        try {
            banner.setUpdateTime(LocalDateTime.now());
            boolean success = this.updateById(banner);
            if (success) {
                return Result.success("轮播图更新成功");
            } else {
                return Result.error("轮播图更新失败");
            }
        } catch (Exception e) {
            return Result.error("轮播图更新失败：" + e.getMessage());
        }
    }
    
    @Override
    public Result<String> deleteBanner(Long id) {
        try {
            boolean success = this.removeById(id);
            if (success) {
                return Result.success("轮播图删除成功");
            } else {
                return Result.error("轮播图删除失败");
            }
        } catch (Exception e) {
            return Result.error("轮播图删除失败：" + e.getMessage());
        }
    }
    
    @Override
    public Result<String> toggleBannerStatus(Long id, Boolean isActive) {
        try {
            Banner banner = new Banner();
            banner.setId(id);
            banner.setIsActive(isActive);
            banner.setUpdateTime(LocalDateTime.now());
            
            boolean success = this.updateById(banner);
            if (success) {
                String status = isActive ? "启用" : "禁用";
                return Result.success("轮播图" + status + "成功");
            } else {
                return Result.error("轮播图状态更新失败");
            }
        } catch (Exception e) {
            return Result.error("轮播图状态更新失败：" + e.getMessage());
        }
    }
} 