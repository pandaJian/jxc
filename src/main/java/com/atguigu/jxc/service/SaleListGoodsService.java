package com.atguigu.jxc.service;


import com.atguigu.jxc.domain.SaleVo;
import com.atguigu.jxc.domain.ServiceVO;

import java.util.List;

/**
 * @author panda
 * @date 2021/3/9 10:09
 * Description
 **/
public interface SaleListGoodsService {
    ServiceVO updateState(Integer saleListId);

    List<SaleVo> getSaleDataByDay(String sTime, String eTime);
}
