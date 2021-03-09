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

import java.util.Map;

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

    /**
     * 颜敏烜 day02 4.1
     * 退货单列表展示（可条件查询：单据号模糊、供应商、是否付款和日期区间）
     *
     * 请求URL：http://localhost:8080/returnListGoods/list
     * 请求参数：String returnNumber,Integer supplierId, Integer state,String sTime,String eTime
     * 请求方式：POST
     * 返回值类型：JSON
     * 返回值：Map<String,Object>
     */
    @PostMapping("/list")
    @RequiresPermissions(value = "供应商管理")
    public Map<String,Object> list(String returnNumber, Integer supplierId, Integer state, String sTime, String eTime){

        return returnListGoodsService.list(returnNumber,supplierId,state,sTime,eTime);
    }

    /**
     * 颜敏烜 day02 4.2
     * 退货单商品信息
     *
     * 请求URL：http://localhost:8080/returnListGoods/goodsList
     * 请求参数：Integer returnListId
     * 请求方式：POST
     * 返回值类型：JSON
     * 返回值：Map<String,Object>
     */
    @PostMapping("/goodsList")
    @RequiresPermissions(value = "供应商管理")
    public Map<String,Object> goodsList(Integer returnListId){

        return returnListGoodsService.goodsList(returnListId);
    }

    /**
     * 颜敏烜 day02 4.3
     * 删除退货单
     *
     * 请求URL：http://localhost:8080/returnListGoods/delete
     * 请求参数：Integer returnListId
     * 请求方式：POST
     * 返回值类型：JSON
     * 返回值：ServiceVO
     */
    @PostMapping("/delete")
    @RequiresPermissions(value = "供应商管理")
    public ServiceVO delete(Integer returnListId){

        return returnListGoodsService.delete(returnListId);
    }
}
