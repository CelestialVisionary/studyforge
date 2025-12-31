package com.exam.service;

import com.exam.entity.Category;
import java.util.List;

public interface CategoryService {
    
    // 获取分类树
    List<Category> getCategoryTree();
    
    // 获取所有分类列表
    List<Category> getAllCategories();
    
    // 添加分类
    void addCategory(Category category);
    
    // 更新分类
    void updateCategory(Category category);
    
    // 删除分类
    void deleteCategory(Long id);
} 