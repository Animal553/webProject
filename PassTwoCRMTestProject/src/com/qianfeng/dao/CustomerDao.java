package com.qianfeng.dao;

import com.qianfeng.entity.Customer;
import java.sql.Connection;
import java.util.List;

public interface CustomerDao {
    void addCustomer(Connection connection, String sql, Object... object);
    void deleteCustomer(Connection connection, String sql, Object... object);

    Customer getCustomer(Connection connection, String sql, Class<Customer> clazz, Object... object);
    List<Customer> getCustomerList(Connection connection, String sql, Class<Customer> clazz, Object... object);
    Integer GetCount(Connection connection, String sql);
}
