package com.exam.controller;

import com.exam.config.JwtConfig;
import com.exam.dto.LoginRequest;
import com.exam.dto.LoginResponse;
import com.exam.entity.User;
import com.exam.service.UserService;
import com.exam.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 认证控制器 - 处理用户登录和令牌相关请求
 * 
 * @author 智能学习平台开发团队
 * @version 1.0
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "认证管理", description = "用户登录和令牌相关接口")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final JwtConfig jwtConfig;
    
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, 
            JwtUtils jwtUtils, JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.jwtConfig = jwtConfig;
    }
    
    /**
     * 用户登录
     * 
     * @param loginRequest 登录请求
     * @return 登录响应
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录并获取JWT令牌")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 认证用户
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            
            // 获取认证的用户详情
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            // 查询完整的用户信息
            User user = userService.getUserByUsername(userDetails.getUsername());
            
            // 生成JWT令牌
            String token = jwtUtils.generateToken(user.getUsername(), user.getId(), user.getRole());
            
            // 构建响应
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwtConfig.getTokenPrefix() + token);
            loginResponse.setUserId(user.getId());
            loginResponse.setUsername(user.getUsername());
            loginResponse.setRealName(user.getRealName());
            loginResponse.setRole(user.getRole());
            
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(null);
        }
    }
}
