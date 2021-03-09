package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.PurchaseDao;
import com.atguigu.jxc.dao.PurchaseListGoodsDao;
import com.atguigu.jxc.domain.CountVo;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;
import com.atguigu.jxc.service.PurchaseService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : panda Jian
 * @date : 2021-03-08 15:05
 * Description
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseDao purchaseDao;
    @Autowired
    PurchaseListGoodsDao purchaseListGoodsDao;
    @Autowired
    private GoodsDao goodsDao;


    @Override
    public ServiceVO save(String purchaseNumber, PurchaseList purchaseList, String purchaseListGoodsStr) {
        //保存PurchaseList
        purchaseDao.savePurchaseList(purchaseList);
        Integer purchaseListId = purchaseList.getPurchaseListId();
        System.out.println(purchaseList);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<PurchaseListGoods>>() {}.getType();
        List<PurchaseListGoods> purchaseListGoods = gson.fromJson(purchaseListGoodsStr,type);
        purchaseListGoods.forEach(purchaseListGoods1 -> {
            purchaseListGoods1.setPurchaseListId(purchaseListId);
            purchaseListGoodsDao.saveListGoods(purchaseListGoods1);
            Goods byGoodsId = goodsDao.findByGoodsId(purchaseListGoods1.getGoodsId());
            Integer oldNum = byGoodsId.getInventoryQuantity();
            Integer goodsNum = purchaseListGoods1.getGoodsNum();
            Goods goods = new Goods();
            goods.setGoodsId(purchaseListGoods1.getGoodsId());
            goods.setInventoryQuantity(oldNum + goodsNum);
            goodsDao.updateGoods(goods);
        });
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @Override
    public List<Map<String,Object>> goodsList(Integer purchaseListId) {
        return purchaseDao.goodsList(purchaseListId);
    }

    @Override
    public ServiceVO delete(Integer purchaseListId) {
        Integer integer = purchaseDao.delete(purchaseListId);
        if (integer > 0){
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }
        return new ServiceVO(ErrorCode.TIME_OUT_CODE,"删除失败");
    }

    @Override
    public List<CountVo> count(String sTime, String eTime, Integer goodsTypeId, String codeOrName) {
        List<CountVo> countVos = purchaseDao.count(sTime,eTime,goodsTypeId,codeOrName);
        return countVos;
    }
}
