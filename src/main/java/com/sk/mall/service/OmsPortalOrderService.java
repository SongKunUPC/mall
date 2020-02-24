package com.sk.mall.service;

import com.sk.mall.common.api.CommonResult;
import com.sk.mall.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理service
 * Created by SongKun on 2020/2/15 8:57 下午
 */
public interface OmsPortalOrderService {


    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
