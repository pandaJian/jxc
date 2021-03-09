package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.*;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.ReturnListGoodsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ReturnListGoodsServiceImpl implements ReturnListGoodsService {

    @Autowired
    private LogService logService;
    @Autowired
    private ReturnListDao returnListDao;
    @Autowired
    private ReturnListGoodsDao returnListGoodsDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SupplierDao supplierDao;

    Gson gson = new Gson();

    @Override
    public ServiceVO save(ReturnList returnList, String returnListGoodsStr) {

        //保存退货清单PurchaseList相关信息
        returnListDao.save(returnList);
        Integer returnListId = returnList.getReturnListId();
        //User user = userDao.findUserByName(returnList.getSupplierName());
        returnList.setUserId(1);
        logService.save(new Log(Log.INSERT_ACTION,"保存退货清单:"+returnList.getReturnNumber()));
        //将商品列表json反序列化
        List<ReturnListGoods> returnListGoods = gson.fromJson(returnListGoodsStr, new TypeToken<List<ReturnListGoods>>(){}.getType());
        if(!CollectionUtils.isEmpty(returnListGoods))
        {
            returnListGoods.forEach( returnListGood -> {
                //更新商品数量
                Goods byGoodsId = goodsDao.findByGoodsId(returnListGood.getGoodsId());
                byGoodsId.setInventoryQuantity(byGoodsId.getInventoryQuantity()-returnListGood.getGoodsNum());
                goodsDao.updateGoods(byGoodsId);
                logService.save(new Log(Log.UPDATE_ACTION,"更新商品库存:"+byGoodsId.getGoodsId()));
                //保存退货单商品列表ReturnListGoods相关信息
                returnListGood.setReturnListId(returnListId);
                returnListGoodsDao.save(returnListGood);
                logService.save(new Log(Log.INSERT_ACTION,"保存退货单商品列表:"+returnListGood.getReturnListGoodsId()));
            });
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }
        return new ServiceVO(ErrorCode.NULL_POINTER_CODE,ErrorCode.NULL_POINTER_MESS);

    }

    @Override
    public Map<String, Object> list(String returnNumber, Integer supplierId, Integer state, String sTime, String eTime) {
        List<ReturnList> returnLists = returnListDao.list(returnNumber, supplierId, state, sTime, eTime);
        returnLists.forEach(returnList -> {
            returnList.setSupplierName(supplierDao.getSupplierById(returnList.getSupplierId()).getSupplierName());
            returnList.setTrueName(userDao.getUserById(1).getTrueName());
            logService.save(new Log(Log.SELECT_ACTION,"查询退货清单列表:"+returnList.getReturnNumber()));
        });

        Map<String, Object> map = new HashMap<>();
        map.put("rows", returnLists);

        return map;
    }

    @Override
    public Map<String, Object> goodsList(Integer returnListId) {

        List<ReturnListGoods> returnListGoodsList = returnListGoodsDao.queryReturnListById(returnListId);

        returnListGoodsList.forEach(returnListGoods -> {
            logService.save(new Log(Log.SELECT_ACTION,"查询退货清单商品列表:"+returnListGoods.getGoodsId()));
        });

        Map<String, Object> map = new HashMap<>();
        map.put("rows", returnListGoodsList);

        return map;
    }

    @Override
    public ServiceVO delete(Integer returnListId) {

        //TODO:两个删除应该保证原子性，添加事务
        Integer i2 = returnListGoodsDao.deleteByReturnListId(returnListId);
        logService.save(new Log(Log.DELETE_ACTION,"删除退货清单商品列表:"+returnListId));
        Integer i1 = returnListDao.deleteById(returnListId);
        logService.save(new Log(Log.DELETE_ACTION,"删除退货清单:"+returnListId));


        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        //return new ServiceVO(ErrorCode.NULL_POINTER_CODE,ErrorCode.NULL_POINTER_MESS);
    }
}
