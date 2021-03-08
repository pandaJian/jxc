package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : panda Jian
 * @date : 2021-03-08 14:59
 * Description
 */
@RestController
@RequestMapping("/purchaseListGoods")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping(value = "/save")
    public ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr){
        String purchaseNumber = purchaseList.getPurchaseNumber();
        return purchaseService.save(purchaseNumber,purchaseList,purchaseListGoodsStr);
    }
}
