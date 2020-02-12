package com.sk.mall.dao;

import com.sk.mall.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 * Created by SongKun on 2020/2/9 6:51 下午
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用户所有权限（包括+-权限）
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
