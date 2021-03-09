package com.atguigu.jxc.dao;

import com.atguigu.jxc.domain.CountVo;
import com.atguigu.jxc.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 客户dao
 */
public interface CustomerDao {

    List<Customer> getCustomerList(@Param("offSet") Integer offSet, @Param("pageRow") Integer pageRow, @Param("customerName") String customerName);

    Integer getCustomerCount(@Param("customerName") String customerName);

    Integer saveCustomer(Customer customer);

    Integer updateCustomer(Customer customer);

    Customer getCustomerById(Integer customerId);

    Integer deleteCustomer(Integer customerId);

    List<Customer> getComboboxList(String q);


    List<CountVo> count(String sTime, String eTime, Integer goodsTypeId, String codeOrName);
}
