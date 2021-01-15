package com.qianfeng.service.Impl;

import com.qianfeng.dao.CategoryDao;
import com.qianfeng.dao.EmployeeDao;
import com.qianfeng.dao.Impl.CategoryDaoImpl;
import com.qianfeng.dao.Impl.EmployeeDaoImpl;
import com.qianfeng.dao.Impl.ProductDaoImpl;
import com.qianfeng.dao.ProductDao;
import com.qianfeng.dto.Page;
import com.qianfeng.entity.Category;
import com.qianfeng.entity.Employee;
import com.qianfeng.entity.Product;
import com.qianfeng.service.ProductService;
import com.qianfeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao = new ProductDaoImpl();
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public void addProduct(Product product) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "insert into product(product_id,product_name,category_id,price,create_id,create_time,update_time,status) values(default,?,?,?,?,?,?,?)";
            productDao.addProduct(connection,sql,product.getProductName(),product.getCategoryId(),product.getPrice(),product.getCreateId(),product.getCreateTime(),product.getUpdateTime(),product.getStatus());
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
    }

    @Override
    public void deleteProduct(Integer productId) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "delete from product where product_id = ?";
            productDao.deleteProduct(connection,sql,productId);
            connection.commit();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
    }

    @Override
    public Page getProductPage(Integer pageNo, Integer pageSize) {
        Page page = new Page();
        Connection connection = null;
        List<Product> products = null;
        connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select product_id as productId , product_name as productName ,category_id as categoryId, price , create_id as createId, create_time as createTime ,update_time as updateTime,status from product limit ?,?";
            products = productDao.getProductList(connection,sql,Product.class,(pageNo-1)*pageSize,pageSize);
            for (Product product : products) {
                if (product.getStatus()==1){
                    product.setShelf("上架");
                }else {
                    product.setShelf("下架");
                }
            }
            String sqlE = "select emp_name as empName from employee where emp_id = ?";
            for (Product product : products) {
                Employee employee = employeeDao.getEmployee(connection,sqlE, Employee.class,product.getCreateId());
                product.setEmpName(employee.getEmpName());
            }
            String sqlCA = "select category_name as categoryName from category where category_id = ?";
            for (Product product : products) {
                Category category = categoryDao.getCategory(connection,sqlCA,Category.class,product.getCategoryId());
                product.setCategoryName(category.getCategoryName());
            }

            String sqlC = "select count(*) as count from product";
            Integer count = productDao.GetCount(connection,sqlC);
            page.setPageMessage(products);
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            page.setPageCount(count);
            int pageCount = (count%pageSize==0)?(count/pageSize):(count/pageSize)+1;
            page.setPageCount(pageCount);

            if (pageNo<=1){
                page.setHasPer(false);
            }else {
                page.setHasPer(true);
            }

            if (pageNo>=pageCount){
                page.setHasNext(false);
            }else {
                page.setHasNext(true);
            }
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
        return page;
    }

    @Override
    public List<Product> selectProductList() {
        List<Product> productList = null;
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select product_id as productId , product_name as productName ,category_id as categoryId, price , create_id as createId, create_time as createTime ,update_time as updateTime,status from product";
            productList = productDao.getProductList(connection,sql,Product.class);
            for (Product product : productList) {
                if (product.getStatus()==1){
                    product.setShelf("上架");
                }else {
                    product.setShelf("下架");
                }
            }
            String sqlE = "select emp_name as empName from employee where emp_id = ?";
            for (Product product : productList) {
                Employee employee = employeeDao.getEmployee(connection,sqlE, Employee.class,product.getCreateId());
                product.setEmpName(employee.getEmpName());
            }
            String sqlCA = "select category_name as categoryName from category where category_id = ?";
            for (Product product : productList) {
                Category category = categoryDao.getCategory(connection,sqlCA,Category.class,product.getCategoryId());
                product.setCategoryName(category.getCategoryName());
            }
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
        return productList;
    }

    @Override
    public Product selectProduct(Integer productId) {
        Connection connection = JDBCUtils.getConnection();
        Product product =null;
        try {
            connection.setAutoCommit(false);
            String sql = "select product_id as productId , product_name as productName ,category_id as categoryId, price , create_id as createId, create_time as createTime ,update_time as updateTime,status from product where product_id = ?";
            product = productDao.getProduct(connection,sql,Product.class,productId);
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
        return product;
    }
}
