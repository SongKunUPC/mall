package com.sk.mall.service;

import com.sk.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;
/**
 * 商品搜索管理Service
 * Created by SongKun on 2020/2/13 9:10 下午
 */
public interface EsProductService {


    /**
     * 从数据库中导入所有商品到ES
     * @return 返回成功导入的个数
     */
    int importAll();

    /**
     * 根据id删除商品
     * @param id 商品id
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     * @param id 商品id
     */
    EsProduct create(Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字、名称、或者副标题搜索
     */
    Page<EsProduct> search(String keyword,Integer pageNum, Integer pageSize);
}
