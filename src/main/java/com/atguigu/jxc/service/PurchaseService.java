package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;

/**
 * @author : panda Jian
 * @date : 2021-03-08 15:04
 * Description
 */
public interface PurchaseService {
    ServiceVO save(String purchaseNumber, PurchaseList purchaseList, String purchaseListGoodsStr);
}
