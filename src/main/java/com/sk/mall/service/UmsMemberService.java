package com.sk.mall.service;

import com.sk.mall.common.api.CommonResult;

/**
 * 会员管理Service
 * Created by SongKun on 2020/2/8 3:20 下午
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone,String authCode);

}
