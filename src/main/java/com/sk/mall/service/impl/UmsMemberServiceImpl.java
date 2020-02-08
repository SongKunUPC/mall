package com.sk.mall.service.impl;

import com.sk.mall.common.api.CommonResult;
import com.sk.mall.service.RedisService;
import com.sk.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * Created by SongKun on 2020/2/8 3:22 下午
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTO_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;


    @Override
    public CommonResult generateAuthCode(String telephone) {
        // 生成6位随机岁
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i =0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        // 验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTO_CODE + telephone,sb.toString());
        // 设置key的过期时间
        redisService.expire(REDIS_KEY_PREFIX_AUTO_CODE + telephone,AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(),"获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if(StringUtils.isEmpty(authCode)){
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTO_CODE+telephone);
        boolean result = authCode.equals(realAuthCode);
        if(result){
            return CommonResult.success(null,"验证码效验成功");
        }else {
            return CommonResult.failed("验证码不正确");
        }
    }
}
