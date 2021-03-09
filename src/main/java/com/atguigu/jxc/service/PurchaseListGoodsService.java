package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;

import java.util.Map;

public interface PurchaseListGoodsService {
    ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr);

    Map<String, Object> list(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime);

    Map<String, Object> goodsList(Integer purchaseListId);

    ServiceVO delete(Integer purchaseListId);
}
