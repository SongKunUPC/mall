package com.sk.mall.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 订单超时取消并解锁库存的定时器
 * Created by SongKun on 2020/2/13 2:30 下午
 */
public class OrderTimeOutCancelTask {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * cron表达式：
     * 每十分钟扫描一次，扫描设定超时时间之前下的订单，如果没有支付则取消该订单
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder(){
        // TODO:  此处应调用取消订单的方法，
        LOGGER.info("取消订单，并根据SKU编号释放锁定库存");
    }
}
