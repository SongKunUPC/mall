package com.sk.mall.controller;

import com.sk.mall.common.api.CommonResult;
import com.sk.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 会员登录注册管理Controller
 * Created by SongKun on 2020/2/8 3:34 下午
 */
@Api(tags = "UmsMemberController",description = "会员登录注册管理")
@RestController
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    public CommonResult getAuthCode(@RequestParam String telephone){
        return memberService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping("/verifyAuthCode")
    public CommonResult verifyAuthCode(@RequestParam String telephone,
                                       @RequestParam String authCode){
        return memberService.verifyAuthCode(telephone,authCode);
    }


}
