package com.atguigu.jxc.dao;


import com.atguigu.jxc.domain.CountVo;
import com.atguigu.jxc.domain.TongJi;

import java.util.List;

/**
 * @author panda
 * @date 2021/3/9 10:11
 * Description
 **/
public interface SaleListGoodsDao {
    Integer updateState(Integer saleListId);

    List<TongJi> queryJinHuo(String sTime, String eTime);

    List<TongJi> queryShouMai(String sTime, String eTime);

    List<CountVo> count(String sTime, String eTime, Integer goodsTypeId, String codeOrName);
}
