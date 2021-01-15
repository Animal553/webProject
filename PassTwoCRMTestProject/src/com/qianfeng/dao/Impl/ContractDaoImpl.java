package com.qianfeng.dao.Impl;

import com.qianfeng.dao.BaseDao;
import com.qianfeng.dao.ContractDao;
import com.qianfeng.entity.Contract;

import java.sql.Connection;
import java.util.List;

public class ContractDaoImpl extends BaseDao implements ContractDao {
    @Override
    public void addContract(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public void deleteContract(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public Contract getContract(Connection connection, String sql, Class<Contract> clazz, Object... object) {
        return get(connection,sql,clazz,object);
    }

    @Override
    public List<Contract> getContractList(Connection connection, String sql, Class<Contract> clazz, Object... object) {
        return getList(connection,sql,clazz,object);
    }

    @Override
    public Integer GetCount(Connection connection, String sql) {
        return getCount(connection,sql);
    }

    @Override
    public int addAndReturnContract(Connection connection, String sql, Object... object) {
        int i = add(connection,sql,object);
        return i;
    }

    @Override
    public void updateContract(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }
}
