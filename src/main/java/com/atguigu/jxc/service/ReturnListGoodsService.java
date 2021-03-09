package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.ReturnList;

public interface ReturnListGoodsService {
    ServiceVO save(ReturnList returnList, String returnListGoodsStr);
}
