package com.atguigu.jxc.controller;

import com.atguigu.jxc.service.PurchaseListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author : panda Jian
 * @date : 2021-03-08 18:37
 * Description
 */
@RestController
@RequestMapping("/purchaseListGoods")
public class ReturnListGoodsController {

    @Autowired
    private PurchaseListGoodsService purchaseListGoodsService;

    @PostMapping("list")
    public List<Map<String, Object>> list(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime){
        List<Map<String, Object>> map = purchaseListGoodsService.queryBy(purchaseNumber, supplierId, state, sTime, eTime);
        return map;
    }
}
