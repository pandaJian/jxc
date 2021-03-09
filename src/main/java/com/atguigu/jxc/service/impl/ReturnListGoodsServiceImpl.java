package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.ReturnListDao;
import com.atguigu.jxc.dao.ReturnListGoodsDao;
import com.atguigu.jxc.dao.UserDao;
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

import java.util.List;


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
}
