package com.atguigu.jxc.dao;


import com.atguigu.jxc.entity.ReturnListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnListGoodsDao {

    Integer save(ReturnListGoods returnListGoods);

    List<ReturnListGoods> queryReturnListById(@Param("returnListId") Integer returnListId);

    Integer deleteByReturnListId(@Param("returnListId") Integer returnListId);
}
