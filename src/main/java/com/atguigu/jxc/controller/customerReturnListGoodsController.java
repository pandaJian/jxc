package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.CountVo;
import com.atguigu.jxc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : panda Jian
 * @date : 2021-03-09 14:36
 * Description
 */
@RestController
@RequestMapping("/customerReturnListGoods")
public class customerReturnListGoodsController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/count")
    public List<CountVo> count(String sTime, String eTime , Integer goodsTypeId, String codeOrName){
        List<CountVo> countVos = customerService.count(sTime,eTime,goodsTypeId,codeOrName);
        return countVos;
    }

}
