package com.sk.mall.controller;

import com.sk.mall.common.api.CommonResult;
import com.sk.mall.dto.UmsAdminLoginParam;
import com.sk.mall.mbg.model.UmsAdmin;
import com.sk.mall.mbg.model.UmsPermission;
import com.sk.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
 * Created by SongKun on 2020/2/12 9:37 下午
 */
@Api(tags = "UmsAdminController",description = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result){
        System.out.println("开始注册");
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null){
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam,BindingResult result){
        System.out.println("........");
        String token = adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
        if(token == null){
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("tokenHead",tokenHead);
        tokenMap.put("token",token);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户的所以权限（包括+-权限）")
    @GetMapping("/permission/{adminId}")
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId){
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
