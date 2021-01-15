package com.qianfeng.dao.Impl;

import com.qianfeng.dao.BaseDao;
import com.qianfeng.dao.CustomerDao;
import com.qianfeng.entity.Customer;

import java.sql.Connection;
import java.util.List;

public class CustomerDaoImpl extends BaseDao implements CustomerDao {
    @Override
    public void addCustomer(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public void deleteCustomer(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public Customer getCustomer(Connection connection, String sql, Class<Customer> clazz, Object... object) {
        return get(connection,sql,clazz,object);
    }

    @Override
    public List<Customer> getCustomerList(Connection connection, String sql, Class<Customer> clazz, Object... object) {
        return getList(connection,sql,clazz,object);
    }

    @Override
    public Integer GetCount(Connection connection, String sql) {
        return getCount(connection,sql);
    }
}
