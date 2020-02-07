package com.sk.mall.controller;

import com.sk.mall.common.api.CommonPage;
import com.sk.mall.common.api.CommonResult;
import com.sk.mall.mbg.model.PmsBrand;
import com.sk.mall.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 拼盘管理Controller
 * Created by SongKun on 2020/2/7 4:04 下午
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);


    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(brandService.listAllBrand());

    }

    @PostMapping("/create")
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        // @RequestBody可以省略
        CommonResult commonResult;
        int count = brandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @PostMapping("/update/{id}")
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = brandService.updateBrand(id, pmsBrand);
        if (count == 1){
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("updateBrand success:{}",pmsBrand);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}",pmsBrand);
        }
        return commonResult;
    }

    @GetMapping("/delete/{id}")
    public CommonResult deleteBrand(@PathVariable("id") Long id){
        int count = brandService.deleteBrand(id);
        if(count == 1){
            LOGGER.debug("deleteBrand success: id={}",id);
            return CommonResult.success(null);
        }else {
            LOGGER.debug("deleteBrand failed: id={}",id);
            return CommonResult.failed("操作失败");
        }
    }

    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize){
        List<PmsBrand> brandList = brandService.listBrand(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @GetMapping("/{id}")
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id){
        return CommonResult.success(brandService.getBrand(id));
    }
}
