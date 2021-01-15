package com.qianfeng.dao;

import com.qianfeng.entity.Employee;
import com.qianfeng.entity.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductDao {
    void addProduct(Connection connection, String sql, Object... object);
    void deleteProduct(Connection connection, String sql, Object... object);

    Product getProduct(Connection connection, String sql, Class<Product> clazz, Object... object);
    List<Product> getProductList(Connection connection, String sql, Class<Product> clazz, Object... object);
    Integer GetCount(Connection connection, String sql);
}
