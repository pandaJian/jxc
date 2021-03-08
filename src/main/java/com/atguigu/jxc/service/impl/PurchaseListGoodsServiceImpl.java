package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.PurchaseListGoodsDao;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : panda Jian
 * @date : 2021-03-08 20:20
 * Description
 */
@Service
public class PurchaseListGoodsServiceImpl implements PurchaseListGoodsService {

    @Autowired
    PurchaseListGoodsDao purchaseListGoodsDao;

    public List<Map<String, Object>> queryBy(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime) {
        return purchaseListGoodsDao.queryBy(purchaseNumber,supplierId,state,sTime,eTime);
    }
}
