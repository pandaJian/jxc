package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 颜敏烜 day01
 * 商品采购清单
 */
@RestController
@RequestMapping("/purchaseListGoods")
public class PurchaseListGoodsController {

    @Autowired
    private PurchaseListGoodsService purchaseListGoodsService;

    /**
     * 颜敏烜 day01 1.2
     * 保存进货清单
     *
     * 请求URL示例：http://localhost:8080/purchaseListGoods/save?purchaseNumber=JH1605768306735
     * 请求参数：PurchaseList purchaseList, String purchaseListGoodsStr
     * 请求方式：POST
     * 返回值类型：JSON
     * 返回值：ServiceVO
     */
    @PostMapping("/save")
    @RequiresPermissions(value = "供应商管理")
    public ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr){

        return purchaseListGoodsService.save(purchaseList,purchaseListGoodsStr);
    }
    /**
     * 颜敏烜 day01 3.1
     * 进货单列表展示
     *
     * 请求URL：http://localhost:8080/purchaseListGoods/list
     * 请求参数：String purchaseNumber, Integer supplierId, Integer state, String sTime,String eTime
     * 请求方式：POST
     * 返回值类型：JSON
     * 返回值：Map<String,Object>
     */
    @PostMapping("/list")
    @RequiresPermissions(value = "供应商管理")
    public Map<String,Object> list(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime){

        return purchaseListGoodsService.list(purchaseNumber,supplierId,state,sTime,eTime);
        //return null;
    }
    /**
     * 颜敏烜 day02 3.2
     * 查询进货单商品信息
     *
     * 请求URL：http://localhost:8080/purchaseListGoods/goodsList
     * 请求参数：Integer purchaseListId
     * 请求方式：POST
     * 返回值类型：JSON
     * 返回值：Map<String,Object>
     */
    @PostMapping("/goodsList")
    @RequiresPermissions(value = "供应商管理")
    public Map<String,Object> goodsList(Integer purchaseListId){

        return purchaseListGoodsService.goodsList(purchaseListId);
        //return null;
    }
}
