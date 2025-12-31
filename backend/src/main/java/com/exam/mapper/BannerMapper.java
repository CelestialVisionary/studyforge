package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 轮播图Mapper接口
 */
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {
    
    /**
     * 获取启用的轮播图，按排序顺序升序排列
     * @return 轮播图列表
     */
    @Select("SELECT * FROM banners WHERE is_active = true ORDER BY sort_order ASC")
    List<Banner> selectActiveBanners();
    
} 