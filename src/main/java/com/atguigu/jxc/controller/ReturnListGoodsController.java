package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.CountVo;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : panda Jian
 * @date : 2021-03-08 18:37
 * Description
 */
@RestController
@RequestMapping("/returnListGoods")
public class ReturnListGoodsController {

    @Autowired
    private PurchaseListGoodsService purchaseListGoodsService;

    @PostMapping("/count")
    public List<CountVo> count(String sTime, String eTime , Integer goodsTypeId, String codeOrName){
        List<CountVo> countVos = purchaseListGoodsService.count(sTime,eTime,goodsTypeId,codeOrName);
        return countVos;
    }

}
