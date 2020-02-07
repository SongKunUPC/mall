package com.sk.mall.config;

import com.sk.mall.common.api.CommonResult;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by SongKun on 2020/2/6 11:59 上午
 */
@Configuration
@MapperScan("com.sk.mall.mbg.mapper")
public class MyBatisConfig {
}
