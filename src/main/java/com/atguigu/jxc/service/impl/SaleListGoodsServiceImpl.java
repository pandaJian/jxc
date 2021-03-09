package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SaleListGoodsDao;
import com.atguigu.jxc.domain.*;
import com.atguigu.jxc.service.SaleListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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

        List<String> date = tongJi.stream().map(TongJi::getDate).collect(Collectors.toList());
        List<String> date1 = tongJi1.stream().map(TongJi::getDate).collect(Collectors.toList());

        List<String> strings = new ArrayList<>();
        for (String s : date) {
            for (String s1 : date1) {
                if (s.equals(s1)){
                    strings.add(s);
                }
                strings.add(s);
                strings.add(s1);
            }
        }
        HashSet<String> strings1 = new HashSet<>(strings);
        ArrayList<String> strings2 = new ArrayList<>(strings1);
        System.out.println("strings2 = " + strings2);

        List<SaleVo> saleVos = new ArrayList<>();
        Double num1 = 0.0;
        Double num2 = 0.0;
        for (String s : strings2) {
            for (TongJi ji : tongJi) {
                if (s.equals(ji.getDate())){
                    num1 += ji.getQian();
                }
            }
            for (TongJi ji : tongJi1) {
                if (s.equals(ji.getDate())){
                    num2 += ji.getQian();
                }
            }
            SaleVo saleVo = new SaleVo();
            saleVo.setDate(s);
            saleVo.setPurchasingTotal(num1);
            saleVo.setSaleTotal(num2);
            saleVo.setProfit(num2 - num1);
            saleVos.add(saleVo);
        }
        return saleVos;
    }

    @Override
    public List<CountVo> count(String sTime, String eTime, Integer goodsTypeId, String codeOrName) {

        List<CountVo> countVos = saleListGoodsDao.count(sTime,eTime,goodsTypeId,codeOrName);
        return countVos;

    }
}
