package com.exam.service;

import com.exam.entity.QuestionCategory;

import java.util.List;

/**
 * 真题分类管理Service接口
 */
public interface QuestionCategoryService {
    
    /**
     * 获取分类树形结构列表
     * @return 分类树
     */
    List<QuestionCategory> getCategoryTree();
    
    /**
     * 根据父级ID查询子分类
     * @param parentId 父级ID
     * @return 子分类列表
     */
    List<QuestionCategory> getCategoriesByParentId(Integer parentId);
    
    /**
     * 根据ID查询分类详情
     * @param id 分类ID
     * @return 分类信息
     */
    QuestionCategory getCategoryById(Integer id);
    
    /**
     * 新增分类
     * @param category 分类信息
     * @return 是否成功
     */
    boolean saveCategory(QuestionCategory category);
    
    /**
     * 更新分类信息
     * @param category 分类信息
     * @return 是否成功
     */
    boolean updateCategory(QuestionCategory category);
    
    /**
     * 删除分类
     * @param id 分类ID
     * @return 是否成功
     */
    boolean deleteCategory(Integer id);
    
    /**
     * 查询所有启用状态的分类（用于下拉选择）
     * @return 分类列表
     */
    List<QuestionCategory> getAllEnabledCategories();
    
    /**
     * 检查分类名称是否已存在（同级别下）
     * @param name 分类名称
     * @param parentId 父级ID
     * @param excludeId 排除的ID
     * @return 是否存在
     */
    boolean isNameExists(String name, Integer parentId, Integer excludeId);
    
    /**
     * 更新分类排序
     * @param id 分类ID
     * @param sortOrder 排序序号
     * @return 是否成功
     */
    boolean updateSortOrder(Integer id, Integer sortOrder);
} 