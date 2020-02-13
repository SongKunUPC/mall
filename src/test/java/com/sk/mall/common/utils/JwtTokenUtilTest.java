package com.sk.mall.common.utils;

import com.sk.mall.dto.AdminUserDetails;
import com.sk.mall.mbg.model.UmsAdmin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by SongKun on 2020/2/12 8:40 下午
 */
@SpringBootTest
public class JwtTokenUtilTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void testGenerateToken(){
        UmsAdmin umsAdmin =new UmsAdmin();
        umsAdmin.setUsername("test");
        UserDetails userDetails =new AdminUserDetails(umsAdmin,null);
        System.out.println(jwtTokenUtil.generateToken(userDetails));
    }

    @Test
    public void testGetUserNameFromToken(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb25na3VuIiwiY3JlYXRlZCI6MTU4MTUxMjEwODk4OSwiZXhwIjoxNTgyMTE2OTA4fQ.AWfbsLPM_lmLCYeB4__x8KJDZ-6eZxs2iy4iyneXLd-txp5joWin5zWywdnrrzhOM4SdFk5PUmg-jWACFYYJXw";
        String name = jwtTokenUtil.getUserNameFromToken(token);
        System.out.println(name);
    }

    @Test
    public void testPassword(){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);

    }

}
