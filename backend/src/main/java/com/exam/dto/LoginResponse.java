package com.exam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录响应DTO - 用户登录成功后返回的数据
 */
@Data
@Schema(description = "登录成功响应数据")
public class LoginResponse {
    
    @Schema(description = "用户ID", 
            example = "1")
    private Long userId; // 用户ID
    
    @Schema(description = "用户名", 
            example = "admin")
    private String username; // 用户名
    
    @Schema(description = "用户真实姓名", 
            example = "管理员")
    private String realName; // 真实姓名
    
    @Schema(description = "用户角色", 
            example = "ADMIN", 
            allowableValues = {"ADMIN", "TEACHER", "STUDENT"})
    private String role; // 用户角色
    
    @Schema(description = "登录令牌，用于后续API调用的身份验证", 
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token; // 登录令牌
} 