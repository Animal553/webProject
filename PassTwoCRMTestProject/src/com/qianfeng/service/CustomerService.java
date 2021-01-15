package com.qianfeng.service;

import com.qianfeng.dto.Page;
import com.qianfeng.entity.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);
    void deleteCustomer(Integer customerId);
    void updateCustomer(Customer customer);
    Page getCustomerPage(Integer pageNo, Integer pageSize);
    Page getThreadPage(Integer pageNo, Integer pageSize);
    Page getInternationalPage(Integer pageNo, Integer pageSize);
    List<Customer> selectCustomerList();
    Customer selectCustomer(Integer customerId);
}
