package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.PurchaseListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseListGoodsDao {

    Integer save(PurchaseListGoods purchaseListGood);

    List<PurchaseListGoods> queryPurchaseListById(@Param("purchaseListId") Integer purchaseListId);

    Integer deleteByPurchaseListId(@Param("purchaseListId")Integer purchaseListId);

}
