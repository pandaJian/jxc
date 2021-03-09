package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SaleListGoodsDao;
import com.atguigu.jxc.domain.SaleVo;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.domain.TongJi;
import com.atguigu.jxc.service.SaleListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : panda Jian
 * @date : 2021-03-09 10:09
 * Description
 */
@Service
public class SaleListGoodsServiceImpl implements SaleListGoodsService {

    @Autowired
    private SaleListGoodsDao saleListGoodsDao;

    @Override
    public ServiceVO updateState(Integer saleListId) {
        Integer i = saleListGoodsDao.updateState(saleListId);
        if (i > 0){
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }
        return null;
    }

    @Override
    public List<SaleVo> getSaleDataByDay(String sTime, String eTime) {
        List<TongJi> tongJi = saleListGoodsDao.queryJinHuo(sTime,eTime);
        List<TongJi> tongJi1 = saleListGoodsDao.queryShouMai(sTime, eTime);

        int size1 = tongJi.size();
        List<SaleVo> list = new ArrayList<>();
        for (int i = 1; i < size1 - 1; i++) {
            SaleVo saleVo = new SaleVo();
            saleVo.setDate(tongJi.get(i-1).getDate());
            saleVo.setSaleTotal(tongJi1.get(i-1).getQian());
            saleVo.setPurchasingTotal(tongJi.get(i-1).getQian());
            saleVo.setProfit(tongJi1.get(i-1).getQian() - tongJi.get(i-1).getQian());
            list.add(saleVo);
        }
        return list;
    }
}
