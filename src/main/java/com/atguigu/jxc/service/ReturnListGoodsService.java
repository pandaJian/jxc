package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.ReturnList;

import java.util.Map;

public interface ReturnListGoodsService {
    ServiceVO save(ReturnList returnList, String returnListGoodsStr);

    Map<String, Object> list(String returnNumber, Integer supplierId, Integer state, String sTime, String eTime);

    Map<String, Object> goodsList(Integer returnListId);

    ServiceVO delete(Integer returnListId);
}
