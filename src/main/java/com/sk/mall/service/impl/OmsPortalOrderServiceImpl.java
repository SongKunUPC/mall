package com.sk.mall.service.impl;

import com.sk.mall.common.api.CommonResult;
import com.sk.mall.component.CancelOrderSender;
import com.sk.mall.dto.OrderParam;
import com.sk.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SongKun on 2020/2/15 9:07 下午
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;


    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        // todo 执行一系列下单操作...
        LOGGER.info("process generateOrder");
        // 下单完成后开启一个延迟消息，用于当前用户没有付款时取消订单(orderId 应在下单后生成)
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null,"下单成功");
    }

    private void sendDelayMessageCancelOrder(long orderId) {
        // 获取订单超时时间，假设为60分钟（测试用30秒）
        long delayTime = 30 * 1000;
        cancelOrderSender.sendMessage(orderId,delayTime);
    }

    @Override
    public void cancelOrder(Long orderId) {
        // todo：执行一系列取消订单操作
        LOGGER.info("process cancelOrder orderId:{}",orderId);
    }
}
