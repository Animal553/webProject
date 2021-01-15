package com.qianfeng.dao.Impl;

import com.qianfeng.dao.BaseDao;
import com.qianfeng.dao.ContractProductDao;
import com.qianfeng.entity.ContractProduct;

import java.sql.Connection;
import java.util.List;

public class ContractProductDaoImpl extends BaseDao implements ContractProductDao {
    @Override
    public List<ContractProduct> getContractProductList(Connection connection, String sql, Class<ContractProduct> clazz, Object... object) {
        return getList(connection,sql,clazz,object);
    }

    @Override
    public void addContractProduct(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public void deleteContractProduct(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }
}
