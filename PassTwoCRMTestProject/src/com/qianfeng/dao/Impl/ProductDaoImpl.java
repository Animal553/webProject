package com.qianfeng.dao.Impl;

import com.qianfeng.dao.BaseDao;
import com.qianfeng.dao.ProductDao;
import com.qianfeng.entity.Product;

import java.sql.Connection;
import java.util.List;

public class ProductDaoImpl extends BaseDao implements ProductDao {
    @Override
    public void addProduct(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public void deleteProduct(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public Product getProduct(Connection connection, String sql, Class<Product> clazz, Object... object) {
        return get(connection,sql,clazz,object);
    }

    @Override
    public List<Product> getProductList(Connection connection, String sql, Class<Product> clazz, Object... object) {
        return getList(connection,sql,clazz,object);
    }

    @Override
    public Integer GetCount(Connection connection, String sql) {
        return getCount(connection,sql);
    }
}
