package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.*;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseListGoodsServiceImpl implements PurchaseListGoodsService {
    @Autowired
    private LogService logService;
    @Autowired
    private PurchaseListDao purchaseListDao;
    @Autowired
    private PurchaseListGoodsDao purchaseListGoodsDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SupplierDao supplierDao;

    Gson gson  = new Gson();

    @Override
    public ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr) {

        //保存进货清单PurchaseList相关信息
        purchaseListDao.save(purchaseList);
        Integer purchaseListId = purchaseList.getPurchaseListId();
        //User user = userDao.findUserByName(purchaseList.getSupplierName());
        purchaseList.setUserId(1);
        logService.save(new Log(Log.INSERT_ACTION,"保存进货清单:"+purchaseList.getPurchaseNumber()));
        //将商品列表json反序列化
        List<PurchaseListGoods> purchaseListGoods = gson.fromJson(purchaseListGoodsStr, new TypeToken<List<PurchaseListGoods>>(){}.getType());
        if(!CollectionUtils.isEmpty(purchaseListGoods))
        {
            purchaseListGoods.forEach(purchaseListGood -> {
                //更新商品数量
                Goods byGoodsId = goodsDao.findByGoodsId(purchaseListGood.getGoodsId());
                byGoodsId.setInventoryQuantity(byGoodsId.getInventoryQuantity()+purchaseListGood.getGoodsNum());
                goodsDao.updateGoods(byGoodsId);
                logService.save(new Log(Log.UPDATE_ACTION,"更新商品库存:"+byGoodsId.getGoodsId()));
                //保存进货单商品列表PurchaseListGoods相关信息
                purchaseListGood.setPurchaseListId(purchaseListId);
                purchaseListGoodsDao.save(purchaseListGood);
                logService.save(new Log(Log.INSERT_ACTION,"保存进货单商品列表:"+purchaseListGood.getPurchaseListGoodsId()));
            });
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }

        return new ServiceVO(ErrorCode.NULL_POINTER_CODE,ErrorCode.NULL_POINTER_MESS);
    }

    @Override
    public Map<String, Object> list(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime) {

        List<PurchaseList> purchaseLists = purchaseListDao.list(purchaseNumber, supplierId, state, sTime, eTime);
        purchaseLists.forEach(purchaseList -> {
            purchaseList.setSupplierName(supplierDao.getSupplierById(purchaseList.getSupplierId()).getSupplierName());
            purchaseList.setTrueName(userDao.getUserById(1).getTrueName());
        });
        Map<String, Object> map = new HashMap<>();
        map.put("rows", purchaseLists);
        return map;
    }

    @Override
    public Map<String, Object> goodsList(Integer purchaseListId) {
        
        return null;
    }
}
