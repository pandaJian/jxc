package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.CountVo;
import com.atguigu.jxc.domain.ServiceVO;

import java.util.List;
import java.util.Map;

/**
 * @author : panda Jian
 * @date : 2021-03-08 20:19
 * Description
 */
public interface PurchaseListGoodsService {
    public Map<String, Object> queryBy(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime);

    ServiceVO updateState(Integer purchaseListId);

    List<CountVo> count(String sTime, String eTime, Integer goodsTypeId, String codeOrName);
}
