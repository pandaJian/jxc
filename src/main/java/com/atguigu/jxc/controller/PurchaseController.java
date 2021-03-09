package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.CountVo;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import com.atguigu.jxc.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private PurchaseListGoodsService purchaseListGoodsService;

    @PostMapping("/count")
    public List<CountVo> count(String sTime, String eTime , Integer goodsTypeId, String codeOrName){
        List<CountVo> countVos = purchaseService.count(sTime,eTime,goodsTypeId,codeOrName);
        return countVos;
    }

    @PostMapping("/updateState")
    public ServiceVO updateState(Integer purchaseListId){
        ServiceVO serviceVO = purchaseListGoodsService.updateState(purchaseListId);
        return serviceVO;
    }


    @PostMapping("list")
    public Map<String, Object> list(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime){
        Map<String, Object> map = purchaseListGoodsService.queryBy(purchaseNumber, supplierId, state, sTime, eTime);
        return map;
    }

    @PostMapping("delete")
    public ServiceVO delete(Integer purchaseListId){
        return purchaseService.delete(purchaseListId);
    }

    @PostMapping("goodsList")
    public List<Map<String,Object>> goodsList(Integer purchaseListId){
        List<Map<String,Object>> goodsList = purchaseService.goodsList(purchaseListId);
        return goodsList;
    }

    @PostMapping(value = "/save")
    public ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr){
        String purchaseNumber = purchaseList.getPurchaseNumber();
        return purchaseService.save(purchaseNumber,purchaseList,purchaseListGoodsStr);
    }
}
