package com.sk.mall.service;

import com.sk.mall.nosql.mongobd.document.MemberReadHistory;

import java.util.List;

/**
 * Created by SongKun on 2020/2/15 2:34 下午
 */
public interface MemberReadHistoryService {

    /**
     * 生成浏览记录
     */
    int create(MemberReadHistory memberReadHistory);


    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);


    /**
     * 获取用户浏览记录
     */
    List<MemberReadHistory> list(Long memberId);
}
