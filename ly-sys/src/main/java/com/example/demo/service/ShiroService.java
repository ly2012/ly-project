package com.example.demo.service;

import java.util.Set;
import com.example.demo.entity.SysUserEntity;

/**
 * shiro相关接口
 *
 * @author 
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
