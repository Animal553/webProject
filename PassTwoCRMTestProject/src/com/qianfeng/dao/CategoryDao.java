package com.qianfeng.dao;

import com.qianfeng.entity.Category;
import java.sql.Connection;
import java.util.List;

public interface CategoryDao {
    //查找一个部门
    Category getCategory(Connection connection, String sql, Class<Category> clazz, Object... objects);

    //查找全部的部门
    List<Category> getCategoryList(Connection connection, String sql, Class<Category> clazz, Object... objects);

    Integer GetCount(Connection connection, String sql);
}
