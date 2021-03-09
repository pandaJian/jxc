package com.atguigu.jxc.dao;


import com.atguigu.jxc.domain.CountVo;
import com.atguigu.jxc.entity.PurchaseList;

import java.util.List;
import java.util.Map;

/**
 * @author panda
 * @date 2021/3/8 15:38
 * Description
 **/
public interface PurchaseDao {
    void savePurchaseList(PurchaseList purchaseList);

    List<Map<String,Object>> goodsList(Integer purchaseListId);

    Integer delete(Integer purchaseListId);

    List<CountVo> count(String sTime, String eTime, Integer goodsTypeId, String codeOrName);
}
