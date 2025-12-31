package com.exam.mapper;

import com.exam.entity.QuestionCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 真题分类Mapper接口
 */
@Mapper
public interface QuestionCategoryMapper {
    
    /**
     * 查询所有分类（用于树形展示）
     * @return 分类列表
     */
    List<QuestionCategory> selectAll();
    
    /**
     * 根据父级ID查询子分类
     * @param parentId 父级ID
     * @return 子分类列表
     */
    List<QuestionCategory> selectByParentId(@Param("parentId") Integer parentId);
    
    /**
     * 根据ID查询分类详情
     * @param id 分类ID
     * @return 分类信息
     */
    QuestionCategory selectById(@Param("id") Integer id);
    
    /**
     * 新增分类
     * @param category 分类信息
     * @return 影响行数
     */
    int insert(QuestionCategory category);
    
    /**
     * 更新分类信息
     * @param category 分类信息
     * @return 影响行数
     */
    int update(QuestionCategory category);
    
    /**
     * 删除分类
     * @param id 分类ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id);
    
    /**
     * 查询所有启用状态的分类（用于下拉选择）
     * @return 分类列表
     */
    List<QuestionCategory> selectAllEnabled();
    
    /**
     * 检查分类名称是否已存在（同级别下）
     * @param name 分类名称
     * @param parentId 父级ID
     * @param excludeId 排除的ID（用于更新时检查）
     * @return 存在的数量
     */
    int checkNameExists(@Param("name") String name, 
                       @Param("parentId") Integer parentId,
                       @Param("excludeId") Integer excludeId);
    
    /**
     * 检查分类下是否有子分类
     * @param parentId 父级ID
     * @return 子分类数量
     */
    int countChildren(@Param("parentId") Integer parentId);
    
    /**
     * 检查分类下是否有题目
     * @param categoryId 分类ID
     * @return 题目数量
     */
    int countQuestions(@Param("categoryId") Integer categoryId);
    
    /**
     * 更新排序序号
     * @param id 分类ID
     * @param sortOrder 排序序号
     * @return 影响行数
     */
    int updateSortOrder(@Param("id") Integer id, @Param("sortOrder") Integer sortOrder);
} 