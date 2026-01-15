package com.exam.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * JWT配置类 - 用于管理JWT相关配置
 * 
 * @author 智能学习平台开发团队
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    
    /**
     * JWT签名密钥
     */
    private String secret;
    
    /**
     * JWT过期时间（分钟）
     */
    private long expiration = 30;
    
    /**
     * JWT令牌前缀
     */
    private String tokenPrefix = "Bearer ";
    
    /**
     * JWT令牌头名称
     */
    private String header = "Authorization";
}
