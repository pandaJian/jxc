package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.SaleVo;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.service.SaleListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : panda Jian
 * @date : 2021-03-09 10:08
 * Description
 */
@RestController
@RequestMapping("/saleListGoods")
public class SaleListGoodsController {

    @Autowired
    private SaleListGoodsService saleListGoodsService;


    @PostMapping("getSaleDataByDay")
    public List<SaleVo> getSaleDataByDay(String sTime, String eTime){
        List<SaleVo>  saleVos = saleListGoodsService.getSaleDataByDay(sTime,eTime);
        return saleVos;
    }

    @PostMapping("/updateState")
    public ServiceVO updateState(Integer saleListId){
        ServiceVO serviceVO = saleListGoodsService.updateState(saleListId);
        return serviceVO;
    }

}
