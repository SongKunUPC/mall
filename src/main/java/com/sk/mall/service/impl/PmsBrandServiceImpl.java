package com.sk.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.sk.mall.mbg.mapper.PmsBrandMapper;
import com.sk.mall.mbg.model.PmsBrand;
import com.sk.mall.mbg.model.PmsBrandExample;
import com.sk.mall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SongKun on 2020/2/7 2:22 下午
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int createBrand(PmsBrand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        // 在需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 静态方法即可
        // 紧跟在这个方法后的第一个MyBatis 查询方法会被进行分页。
        PageHelper.startPage(pageNum,pageSize);
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
