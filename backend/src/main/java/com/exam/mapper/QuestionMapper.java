package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exam.entity.Question;
import com.exam.vo.QuestionQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.List;
import java.util.Map;

/**
 * 题目Mapper接口
 * 继承MyBatis Plus的BaseMapper，提供基础的CRUD操作
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    //List<定义一个实体类 categoryId count||Map category_id=14,count=1>
    List<Map<String,Long>> selectCategoryQuestionCount();

    //定一个查询方法,还想使用mybatis-plus分页插件
    //方法规则:返回值必须是IPage方法名(第一个参数必须是IPage【分页数据第几页,每页显示条件】，其他参数)
    IPage<Question> selectQuestionPage(IPage<Question> page, @Param("queryVo") QuestionQueryVo queryVo);
}