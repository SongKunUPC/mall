package com.sk.mall.dto;

import com.sk.mall.dao.UmsAdminRoleRelationDao;
import com.sk.mall.mbg.model.UmsAdmin;
import com.sk.mall.mbg.model.UmsPermission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;


/**
 * Created by SongKun on 2020/2/12 9:09 下午
 */
@SpringBootTest
public class AdminUserDetailsTest {

    @Autowired
    UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Test
    public void testgetAuthorities(){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setUsername("songkun");
        List<UmsPermission> permissionList = umsAdminRoleRelationDao.getPermissionList((long) 3);
        AdminUserDetails adminUserDetails = new AdminUserDetails(umsAdmin,permissionList);
        Collection<? extends GrantedAuthority> authorities = adminUserDetails.getAuthorities();
        System.out.println(authorities);
    }
    
}

