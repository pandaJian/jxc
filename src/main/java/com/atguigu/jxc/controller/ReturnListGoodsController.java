package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.ReturnList;
import com.atguigu.jxc.service.ReturnListGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 颜敏烜 day01
 * 退货出库
 */
@RestController
@RequestMapping("/returnListGoods")
public class ReturnListGoodsController {

    @Autowired
    private ReturnListGoodsService returnListGoodsService;

    /**
     * 颜敏烜 day01 2.1
     * 请求URL：http://localhost:8080/returnListGoods/save?returnNumber=TH1605768591211（退货单号前端生成）
     * 请求参数：ReturnList returnList, String returnListGoodsStr
     * 请求方式：POST
     * 返回值类型：JSON
     * 返回值：ServiceVO
     */
    @PostMapping("/save")
    @RequiresPermissions(value = "供应商管理")
    public ServiceVO save(ReturnList returnList, String returnListGoodsStr){

        return returnListGoodsService.save(returnList,returnListGoodsStr);
    }
}
