package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.PurchaseDao;
import com.atguigu.jxc.dao.PurchaseListGoodsDao;
import com.atguigu.jxc.domain.CountVo;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : panda Jian
 * @date : 2021-03-08 20:20
 * Description
 */
@Service
public class PurchaseListGoodsServiceImpl implements PurchaseListGoodsService {

    @Autowired
    PurchaseListGoodsDao purchaseListGoodsDao;
    @Autowired
    PurchaseDao purchaseDao;

    public Map<String, Object> queryBy(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime) {
        List<PurchaseList> purchaseLists = purchaseListGoodsDao.queryBy(purchaseNumber,supplierId,state,sTime,eTime);
        Map<String, Object> map = new HashMap<>();
        map.put("rows",purchaseLists);
        return map;
    }

    @Override
    public ServiceVO updateState(Integer purchaseListId) {
        Integer i = purchaseListGoodsDao.updateState(purchaseListId);
        if (i > 0){
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }
        return null;
    }

    @Override
    public List<CountVo> count(String sTime, String eTime, Integer goodsTypeId, String codeOrName) {
        List<CountVo> countVos = purchaseListGoodsDao.count(sTime,eTime,goodsTypeId,codeOrName);
        return countVos;
    }
}
