package com.qianfeng.service.Impl;

import com.qianfeng.dao.ContractProductDao;
import com.qianfeng.dao.Impl.ContractProductDaoImpl;
import com.qianfeng.dao.Impl.ProductDaoImpl;
import com.qianfeng.dao.ProductDao;
import com.qianfeng.entity.ContractProduct;
import com.qianfeng.entity.Product;
import com.qianfeng.service.ContractProductService;
import com.qianfeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractProductServiceImpl implements ContractProductService {
    ContractProductDao contractProductDao = new ContractProductDaoImpl();
    ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<ContractProduct> selectContractProductList() {
        Connection connection = JDBCUtils.getConnection();
        List<ContractProduct> contractProductList =new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            String sql = "select cp_id as cpId,contract_id as contractId,product_id as productId,count from contract_product";
            contractProductList = contractProductDao.getContractProductList(connection,sql,ContractProduct.class);
            String sqlP = "select product_name as productName from product where product_id =?";
            for (ContractProduct contractProduct : contractProductList) {
                 Product product = productDao.getProduct(connection,sqlP, Product.class,contractProduct.getProductId());
                 contractProduct.setProductName(product.getProductName());
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
        return contractProductList;
    }

    @Override
    public void addContractProduct(ContractProduct contractProduct) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "insert into contract_product(cp_id,contract_id,product_id,count) values(default,?,?,?)";
            contractProductDao.addContractProduct(connection,sql,contractProduct.getContractId(),contractProduct.getProductId(),contractProduct.getCount());
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
    public List<ContractProduct> selectContractProductList(Integer contractId) {
        Connection connection = JDBCUtils.getConnection();
        List<ContractProduct> contractProductList =new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            String sql = "select cp_id as cpId,contract_id as contractId,product_id as productId,count from contract_product where contract_id = ?";
            contractProductList = contractProductDao.getContractProductList(connection,sql,ContractProduct.class,contractId);
            String sqlP = "select product_name as productName from product where product_id =?";
            for (ContractProduct contractProduct : contractProductList) {
                Product product = productDao.getProduct(connection,sqlP, Product.class,contractProduct.getProductId());
                contractProduct.setProductName(product.getProductName());
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
        return contractProductList;
    }
}
