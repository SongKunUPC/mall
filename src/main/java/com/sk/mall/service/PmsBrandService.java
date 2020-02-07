package com.sk.mall.service;
import com.sk.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * Created by SongKun on 2020/2/7 2:18 下午
 */
public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum,int pageSize);

    PmsBrand getBrand(Long id);
}
