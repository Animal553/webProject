package com.qianfeng.dao;

import com.qianfeng.entity.Contract;
import java.sql.Connection;
import java.util.List;

public interface ContractDao {
    void addContract(Connection connection, String sql, Object... object);
    void deleteContract(Connection connection, String sql, Object... object);
    Contract getContract(Connection connection, String sql, Class<Contract> clazz, Object... object);
    List<Contract> getContractList(Connection connection, String sql, Class<Contract> clazz, Object... object);
    Integer GetCount(Connection connection, String sql);
    int addAndReturnContract(Connection connection, String sql, Object... object);
    void updateContract(Connection connection, String sql, Object... object);
}
