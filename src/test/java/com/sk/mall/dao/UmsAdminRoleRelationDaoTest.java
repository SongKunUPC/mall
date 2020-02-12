package com.sk.mall.dao;

import com.sk.mall.mbg.model.UmsPermission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created by SongKun on 2020/2/12 9:03 下午
 */
@SpringBootTest
public class UmsAdminRoleRelationDaoTest {

    @Autowired
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Test
    public void testGetPermissionList(){
        List<UmsPermission> permissionList = umsAdminRoleRelationDao.getPermissionList((long) 3);
        System.out.println(permissionList);
    }

}
