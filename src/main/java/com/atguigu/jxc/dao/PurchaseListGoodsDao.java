package com.atguigu.jxc.dao;


import com.atguigu.jxc.domain.CountVo;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author panda
 * @date 2021/3/8 16:37
 * Description
 **/
public interface PurchaseListGoodsDao {

    void saveListGoods(PurchaseListGoods purchaseListGoods1);

    List<PurchaseList> queryBy(@Param("purchaseNumber") String purchaseNumber,
                               @Param("supplierId") Integer supplierId,
                               @Param("state") Integer state,
                               @Param("sTime") String sTime,
                               @Param("eTime") String eTime);

    Integer updateState(Integer purchaseListId);

    List<CountVo> count(String sTime, String eTime, Integer goodsTypeId, String codeOrName);
}
