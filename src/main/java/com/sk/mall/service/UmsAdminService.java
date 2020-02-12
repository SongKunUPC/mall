package com.sk.mall.service;

import com.sk.mall.mbg.model.UmsAdmin;
import com.sk.mall.mbg.model.UmsPermission;

import java.util.List;

/**
 * 后台管理service
 * Created by SongKun on 2020/2/8 8:34 下午
 */
public interface UmsAdminService {

    /**
     * 根据用户名取出管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);


    /**
     * 获取用户所有权限
     */
    List<UmsPermission> getPermissionList(Long adminId);
}
