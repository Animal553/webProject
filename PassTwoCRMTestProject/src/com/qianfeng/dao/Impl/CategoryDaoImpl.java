package com.qianfeng.dao.Impl;

import com.qianfeng.dao.BaseDao;
import com.qianfeng.dao.CategoryDao;
import com.qianfeng.entity.Category;

import java.sql.Connection;
import java.util.List;

public class CategoryDaoImpl extends BaseDao implements CategoryDao {
    @Override
    public Category getCategory(Connection connection, String sql, Class<Category> clazz, Object... objects) {
        return get(connection,sql, Category.class,objects);
    }

    @Override
    public List<Category> getCategoryList(Connection connection, String sql, Class<Category> clazz, Object... objects) {
        return getList(connection,sql, Category.class,objects);
    }

    @Override
    public Integer GetCount(Connection connection, String sql) {
        return getCount(connection,sql);
    }
}
