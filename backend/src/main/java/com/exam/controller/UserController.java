package com.exam.controller;

import com.exam.common.Result;
import com.exam.dto.LoginRequest;
import com.exam.dto.LoginResponse;
import com.exam.entity.User;
import com.exam.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 用户控制器 - 处理用户认证和权限管理相关的HTTP请求
 * 包括用户登录、权限验证等功能
 */
@RestController  // REST控制器，返回JSON数据
@RequestMapping("/api/user")  // 用户API路径前缀
@CrossOrigin(origins = "*")  // 允许跨域访问
@Tag(name = "用户管理", description = "用户相关操作，包括登录认证、权限验证等功能")  // Swagger API分组
public class UserController {
    
    /**
     * 注入用户业务服务
     */
    @Autowired
    private UserService userService; // 注入用户服务
    
    /**
     * 用户登录
     * @param loginRequest 登录请求
     * @return 登录结果
     */
    @PostMapping("/login")  // 处理POST请求
    @Operation(summary = "用户登录", description = "用户通过用户名和密码进行登录验证，返回用户信息和token")  // API描述
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 验证参数
        if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            return Result.error("密码不能为空");
        }
        
        // 执行登录
        User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            return Result.error("用户名或密码错误");
        }
        
        // 构建登录响应
        LoginResponse response = new LoginResponse();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setRealName(user.getRealName());
        response.setRole(user.getRole());
        response.setToken(UUID.randomUUID().toString()); // 简单的token生成
        
        return Result.success(response);
    }
    
    /**
     * 检查用户权限
     * @param userId 用户ID
     * @return 权限检查结果
     */
    @GetMapping("/check-admin/{userId}")  // 处理GET请求
    @Operation(summary = "检查管理员权限", description = "验证指定用户是否具有管理员权限")  // API描述
    public Result<Boolean> checkAdmin(
            @Parameter(description = "用户ID") @PathVariable Long userId) {
        boolean isAdmin = userService.isAdmin(userId);
        return Result.success(isAdmin);
    }
} 