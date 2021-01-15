package com.qianfeng.service.Impl;

import com.qianfeng.dao.CategoryDao;
import com.qianfeng.dao.Impl.CategoryDaoImpl;
import com.qianfeng.entity.Category;
import com.qianfeng.service.CategoryService;
import com.qianfeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public Category selectCategory(Integer categoryId) {
        Connection connection = JDBCUtils.getConnection();
        Category category = null;
        try {
            connection.setAutoCommit(false);
            String sql = "select category_id as categoryId , category_name as categoryName from category where category_id = ?";
            category = categoryDao.getCategory(connection,sql,Category.class,categoryId);
            connection.commit();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
        return category;
    }

    @Override
    public List<Category> selectCategoryList() {
        Connection connection = JDBCUtils.getConnection();
        List<Category> categoryList = null;
        try {
            connection.setAutoCommit(false);
            String sql = "select category_id as categoryId , category_name as categoryName from category";
            categoryList = categoryDao.getCategoryList(connection,sql,Category.class);
            connection.commit();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
        return categoryList;
    }
}
