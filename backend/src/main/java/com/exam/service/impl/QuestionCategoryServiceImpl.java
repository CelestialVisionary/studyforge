package com.exam.service.impl;

import com.exam.entity.QuestionCategory;
import com.exam.mapper.QuestionCategoryMapper;
import com.exam.service.QuestionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 真题分类管理Service实现类
 */
@Service
public class QuestionCategoryServiceImpl implements QuestionCategoryService {
    
    @Autowired
    private QuestionCategoryMapper categoryMapper;
    
    /**
     * 获取分类树形结构列表
     */
    @Override
    public List<QuestionCategory> getCategoryTree() {
        // 查询所有分类
        List<QuestionCategory> allCategories = categoryMapper.selectAll();
        
        // 构建树形结构
        return buildCategoryTree(allCategories);
    }
    
    /**
     * 构建分类树形结构
     * @param allCategories 所有分类列表
     * @return 树形结构
     */
    private List<QuestionCategory> buildCategoryTree(List<QuestionCategory> allCategories) {
        // 按父级ID分组
        Map<Integer, List<QuestionCategory>> categoryMap = allCategories.stream()
                .collect(Collectors.groupingBy(QuestionCategory::getParentId));
        
        // 获取顶级分类（parentId = 0）
        List<QuestionCategory> rootCategories = categoryMap.get(0);
        if (rootCategories == null) {
            return new ArrayList<>();
        }
        
        // 递归构建子分类
        for (QuestionCategory rootCategory : rootCategories) {
            buildChildren(rootCategory, categoryMap);
        }
        
        return rootCategories;
    }
    
    /**
     * 递归构建子分类
     * @param parent 父分类
     * @param categoryMap 分类映射
     */
    private void buildChildren(QuestionCategory parent, Map<Integer, List<QuestionCategory>> categoryMap) {
        List<QuestionCategory> children = categoryMap.get(parent.getId());
        if (children != null && !children.isEmpty()) {
            parent.setChildren(children);
            // 递归处理子分类的子分类
            for (QuestionCategory child : children) {
                buildChildren(child, categoryMap);
            }
        }
    }
    
    /**
     * 根据父级ID查询子分类
     */
    @Override
    public List<QuestionCategory> getCategoriesByParentId(Integer parentId) {
        return categoryMapper.selectByParentId(parentId != null ? parentId : 0);
    }
    
    /**
     * 根据ID查询分类详情
     */
    @Override
    public QuestionCategory getCategoryById(Integer id) {
        if (id == null) {
            return null;
        }
        return categoryMapper.selectById(id);
    }
    
    /**
     * 新增分类
     */
    @Override
    public boolean saveCategory(QuestionCategory category) {
        if (category == null || !StringUtils.hasText(category.getName())) {
            return false;
        }
        
        // 设置默认值
        if (category.getParentId() == null) {
            category.setParentId(0);
        }
        if (category.getLevel() == null) {
            category.setLevel(category.getParentId() == 0 ? 1 : 2);
        }
        if (category.getSortOrder() == null) {
            category.setSortOrder(0);
        }
        if (category.getStatus() == null) {
            category.setStatus(1);
        }
        
        // 检查分类名称是否已存在（同级别下）
        if (isNameExists(category.getName(), category.getParentId(), null)) {
            return false;
        }
        
        return categoryMapper.insert(category) > 0;
    }
    
    /**
     * 更新分类信息
     */
    @Override
    public boolean updateCategory(QuestionCategory category) {
        if (category == null || category.getId() == null || !StringUtils.hasText(category.getName())) {
            return false;
        }
        
        // 检查分类名称是否已存在（同级别下，排除当前分类）
        if (isNameExists(category.getName(), category.getParentId(), category.getId())) {
            return false;
        }
        
        return categoryMapper.update(category) > 0;
    }
    
    /**
     * 删除分类
     */
    @Override
    public boolean deleteCategory(Integer id) {
        if (id == null) {
            return false;
        }
        
        // 检查是否有子分类
        int childrenCount = categoryMapper.countChildren(id);
        if (childrenCount > 0) {
            // 有子分类，不允许删除
            return false;
        }
        
        // 检查是否有关联的题目
        int questionCount = categoryMapper.countQuestions(id);
        if (questionCount > 0) {
            // 有关联题目，不允许删除
            return false;
        }
        
        return categoryMapper.deleteById(id) > 0;
    }
    
    /**
     * 查询所有启用状态的分类（用于下拉选择）
     */
    @Override
    public List<QuestionCategory> getAllEnabledCategories() {
        return categoryMapper.selectAllEnabled();
    }
    
    /**
     * 检查分类名称是否已存在（同级别下）
     */
    @Override
    public boolean isNameExists(String name, Integer parentId, Integer excludeId) {
        if (!StringUtils.hasText(name)) {
            return false;
        }
        if (parentId == null) {
            parentId = 0;
        }
        return categoryMapper.checkNameExists(name, parentId, excludeId) > 0;
    }
    
    /**
     * 更新分类排序
     */
    @Override
    public boolean updateSortOrder(Integer id, Integer sortOrder) {
        if (id == null || sortOrder == null) {
            return false;
        }
        return categoryMapper.updateSortOrder(id, sortOrder) > 0;
    }
} 