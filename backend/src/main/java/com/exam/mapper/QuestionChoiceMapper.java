package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.QuestionChoice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionChoiceMapper extends BaseMapper<QuestionChoice> {
    //定义第二步查询方法，根据题目id查询选项集合
    @Select("select * from question_choices where question_id = #{questionId} and is_deleted = 0 order by sort asc;")
    List<QuestionChoice> selectListByQuestionId(Integer questionId);
}