package com.atguigu.jxc.domain;

import lombok.Data;

/**
 * @author : panda Jian
 * @date : 2021-03-09 11:27
 * Description
 */
@Data
public class SaleVo {
    private String date;
    private Double saleTotal;
    private Double purchasingTotal;
    private Double profit;
}
