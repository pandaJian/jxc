package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.CountVo;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;

import java.util.List;
import java.util.Map;

/**
 * @author : panda Jian
 * @date : 2021-03-08 15:04
 * Description
 */
public interface PurchaseService {
    ServiceVO save(String purchaseNumber, PurchaseList purchaseList, String purchaseListGoodsStr);

    List<Map<String,Object>> goodsList(Integer purchaseListId);

    ServiceVO delete(Integer purchaseListId);

    List<CountVo> count(String sTime, String eTime, Integer goodsTypeId, String codeOrName);
}
