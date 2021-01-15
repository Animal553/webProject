package com.qianfeng.dao;

import com.qianfeng.entity.ContractProduct;

import java.sql.Connection;
import java.util.List;

public interface ContractProductDao {
    List<ContractProduct> getContractProductList(Connection connection, String sql, Class<ContractProduct> clazz, Object... object);
    void addContractProduct(Connection connection, String sql, Object... object);
    void deleteContractProduct(Connection connection, String sql, Object... object);
}
