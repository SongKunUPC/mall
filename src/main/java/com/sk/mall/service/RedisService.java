package com.sk.mall.service;

/**
 * redis操作Service
 * 对象和数组都以Json形式进行存储
 * Created by SongKun on 2020/2/8 3:07 下午
 */
public interface RedisService {


    /**
     * 存储数据
     */
    void set(String key,String value);

    /**
     * 获取数据
     */
    String get(String key);


    /**
     * 设置超时时间
     */
    boolean expire(String key,long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key,long delta);
}
