package com.qianfeng.dao.Impl;

import com.qianfeng.dao.BaseDao;
import com.qianfeng.dao.IndustryDao;
import com.qianfeng.entity.Industry;

import java.sql.Connection;
import java.util.List;

public class IndustryDaoImpl extends BaseDao implements IndustryDao {
    @Override
    public void addIndustry(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public void deleteIndustry(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public Industry getIndustry(Connection connection, String sql, Class<Industry> clazz, Object... object) {
        return get(connection,sql,clazz,object);
    }

    @Override
    public List<Industry> getIndustryList(Connection connection, String sql, Class<Industry> clazz, Object... object) {
        return getList(connection,sql,clazz,object);
    }

    @Override
    public Integer GetCount(Connection connection, String sql) {
        return getCount(connection,sql);
    }
}
