package com.exam.config;

import com.exam.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT认证过滤器 - 用于验证请求头中的JWT令牌
 * 
 * @author 智能学习平台开发团队
 * @version 1.0
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final JwtConfig jwtConfig;
    
    @Autowired
    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService, JwtConfig jwtConfig) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.jwtConfig = jwtConfig;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        // 获取请求头中的Authorization
        String authorizationHeader = request.getHeader(jwtConfig.getHeader());
        
        String username = null;
        String token = null;
        
        // 检查Authorization头是否存在且格式正确
        if (authorizationHeader != null && authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            // 提取令牌
            token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
            try {
                // 从令牌中获取用户名
                username = jwtUtils.getUsernameFromToken(token);
            } catch (Exception e) {
                logger.error("无效的JWT令牌: " + e.getMessage(), e);
            }
        }
        
        // 如果令牌有效且用户未被认证
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 加载用户详情
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            // 验证令牌
            if (jwtUtils.validateToken(token)) {
                // 创建认证对象
                UsernamePasswordAuthenticationToken authenticationToken = 
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                // 设置认证详情
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // 设置认证上下文
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        
        // 继续过滤链
        filterChain.doFilter(request, response);
    }
}
