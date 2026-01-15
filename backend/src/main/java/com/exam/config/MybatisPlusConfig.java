package com.exam.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * MyBatis Plus配置类
 * 配置分页插件和其他必要的配置
 */
@Configuration
public class MybatisPlusConfig {
    
    /**
     * 配置MyBatis Plus拦截器
     * 添加分页插件支持
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 注册 Mybatis-Plus 的 MetaObjectHandler，用于自动填充字段
     * @return MetaObjectHandler 实例
     */
    @Bean
    public MetaObjectHandler mybatisMetaObjectHandler() {
        return new MybatisMetaObjectHandler();
    }

    /**
     * 自定义的 MetaObjectHandler 实现类
     */
    public static class MybatisMetaObjectHandler implements MetaObjectHandler {

        /**
         * 插入数据时填充 createTime 和 updateTime
         * @param metaObject MetaObject
         */
        @Override
        public void insertFill(MetaObject metaObject) {
            this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
            this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        }

        /**
         * 更新数据时填充 updateTime
         * @param metaObject MetaObject
         */
        @Override
        public void updateFill(MetaObject metaObject) {
            this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        }
    }
} 