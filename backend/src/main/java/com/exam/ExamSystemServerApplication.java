package com.exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 智能学习平台主启动类
 * 
 * 这是Spring Boot应用程序的入口点，包含以下关键功能：
 * 1. 通过@SpringBootApplication注解启用Spring Boot自动配置
 * 2. 通过@MapperScan注解扫描并注册MyBatis Mapper接口
 * 3. 启动内嵌的Tomcat服务器，提供Web服务
 * 
 * Spring Boot教学要点：
 * - @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
 * - 自动配置会根据classpath中的依赖自动配置Spring应用
 * - 内嵌服务器让部署变得简单，无需外部Tomcat
 * 
 * @author 智能学习平台开发团队
 * @version 1.0
 * @since 2024-01-01
 */
@SpringBootApplication  // Spring Boot核心注解，启用自动配置、组件扫描等功能
@MapperScan("com.exam.mapper")  // 扫描指定包下的MyBatis Mapper接口，自动注册为Spring Bean
public class ExamSystemServerApplication {

    /**
     * 应用程序主入口方法
     * 
     * 执行流程：
     * 1. SpringApplication.run()启动Spring容器
     * 2. 加载配置文件application.yml
     * 3. 初始化数据源、MyBatis等组件
     * 4. 启动内嵌Tomcat服务器
     * 5. 注册所有Controller、Service、Repository等Bean
     * 
     * @param args 命令行参数，可用于传递配置参数
     */
    public static void main(String[] args) {
        // 启动Spring Boot应用，返回ApplicationContext应用上下文
        SpringApplication.run(ExamSystemServerApplication.class, args);
        
        // 输出启动成功信息到控制台
        System.out.println("=================================");
        System.out.println("🎓 智能学习平台启动成功！");
        System.out.println("📖 访问地址：http://localhost:8080");
        System.out.println("💡 技术栈：Spring Boot + MyBatis Plus + MySQL");
        System.out.println("=================================");
    }
} 