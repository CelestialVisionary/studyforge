package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.entity.User;

/**
 * 用户Service接口
 * 定义用户相关的业务方法
 */
public interface UserService extends IService<User> {
    
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    User login(String username, String password);
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    User getUserByUsername(String username);
    
    /**
     * 检查用户是否为管理员
     * @param userId 用户ID
     * @return 是否为管理员
     */
    boolean isAdmin(Long userId);
} 