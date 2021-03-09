package com.atguigu.jxc.dao;


import com.atguigu.jxc.entity.ReturnList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnListDao {

    Integer save(ReturnList returnList);

    List<ReturnList> list(String returnNumber, Integer supplierId, Integer state, String sTime, String eTime);

    Integer deleteById(@Param("returnListId") Integer returnListId);
}
