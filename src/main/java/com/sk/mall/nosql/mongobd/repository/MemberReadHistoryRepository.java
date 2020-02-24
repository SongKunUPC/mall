package com.sk.mall.nosql.mongobd.repository;

import com.sk.mall.nosql.mongobd.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by SongKun on 2020/2/15 2:30 下午
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {


    /**
     * 根据会员id按时间倒序获取浏览记录
     * @param memberId 会员Id
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
