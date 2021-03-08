package com.atguigu.jxc.service;

import java.util.List;
import java.util.Map;

/**
 * @author : panda Jian
 * @date : 2021-03-08 20:19
 * Description
 */
public interface PurchaseListGoodsService {
    public List<Map<String, Object>> queryBy(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime);
}
