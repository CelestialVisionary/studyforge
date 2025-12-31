package com.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS跨域配置类
 * 解决前后端分离开发中的跨域问题
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 配置CORS跨域
     * @param registry CORS注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许所有路径
                .allowedOriginPatterns("*")  // 允许所有来源模式
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的HTTP方法
                .allowedHeaders("*")  // 允许所有请求头
                .allowCredentials(false)  // 不允许发送Cookie，避免与*冲突
                .maxAge(3600);  // 预检请求的有效期，单位为秒
    }

    /**
     * CORS过滤器
     * @return CORS过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // 允许所有来源模式
        configuration.addAllowedOriginPattern("*");
        
        // 允许所有HTTP方法
        configuration.addAllowedMethod("*");
        
        // 允许所有请求头
        configuration.addAllowedHeader("*");
        
        // 不允许发送Cookie，避免与*冲突
        configuration.setAllowCredentials(false);
        
        // 预检请求的有效期
        configuration.setMaxAge(3600L);
        
        // 暴露响应头
        configuration.addExposedHeader("*");
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return new CorsFilter(source);
    }

    /**
     * CORS配置源
     * @return CORS配置源
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // 允许所有来源
        configuration.addAllowedOriginPattern("*");
        
        // 允许所有HTTP方法
        configuration.addAllowedMethod("*");
        
        // 允许所有请求头
        configuration.addAllowedHeader("*");
        
        // 允许发送Cookie
        configuration.setAllowCredentials(true);
        
        // 预检请求的有效期
        configuration.setMaxAge(3600L);
        
        // 暴露响应头
        configuration.addExposedHeader("*");
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
} 