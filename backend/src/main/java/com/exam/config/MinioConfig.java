package com.exam.config;

import io.minio.MinioClient;
import com.exam.config.properties.MinioProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO 配置类
 * 用于配置MinIO对象存储服务
 */
@EnableConfigurationProperties(MinioProperties.class)
@Slf4j
@Configuration
public class MinioConfig {

    @Autowired
    private MinioProperties minioProperties;
    
    /**
     * 创建MinIO客户端Bean
     * @return MinIO客户端实例
     */
    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(minioProperties.getEndPoint())
                        .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                        .build();
        log.info("Minio文件服务器连接成功！连接对象信息为{}", minioClient);
        return minioClient;
    }
} 