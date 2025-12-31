package com.exam.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON数组类型处理器，用于List<String>与JSON字符串的相互转换
 */
public class JsonListTypeHandler extends BaseTypeHandler<List<String>> {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * 设置参数，将List<String>转换为JSON字符串存储到数据库
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        try {
            if (parameter == null || parameter.isEmpty()) {
                ps.setString(i, "[]");  // 空数组
            } else {
                String json = objectMapper.writeValueAsString(parameter);
                ps.setString(i, json);
            }
        } catch (JsonProcessingException e) {
            throw new SQLException("将List转换为JSON失败", e);
        }
    }
    
    /**
     * 从ResultSet中获取结果，将JSON字符串转换为List<String>
     */
    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return parseJson(json);
    }
    
    /**
     * 从ResultSet中获取结果，将JSON字符串转换为List<String>
     */
    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return parseJson(json);
    }
    
    /**
     * 从CallableStatement中获取结果，将JSON字符串转换为List<String>
     */
    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return parseJson(json);
    }
    
    /**
     * 解析JSON字符串为List<String>
     * @param json JSON字符串
     * @return List<String>对象
     */
    private List<String> parseJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        try {
            return objectMapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            // 如果解析失败，返回空列表
            return new ArrayList<>();
        }
    }
} 