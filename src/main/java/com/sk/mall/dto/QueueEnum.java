package com.sk.mall.dto;


/**
 * Created by SongKun on 2020/2/15 4:19 下午
 */
public enum QueueEnum {

    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("mall.order.direct", "mall.order.cancel", "mall.order.cancel"),

    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl", "mall.order.cancel.ttl", "mall.order.cancel.ttl");


    /**
     * 交换器名称
     */
    private String exchange;

    /**
     * 队列名称
     */
    private String name;

    /**
     * 路由键
     */
    private String rounteKey;

    QueueEnum(String exchange, String name, String rounteKey) {
        this.exchange = exchange;
        this.name = name;
        this.rounteKey = rounteKey;
    }

    public String getExchange() {
        return exchange;
    }

    public String getName() {
        return name;
    }

    public String getRounteKey() {
        return rounteKey;
    }
}
