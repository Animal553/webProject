package com.qianfeng.dao.Impl;

import com.qianfeng.dao.BaseDao;
import com.qianfeng.dao.SourceDao;
import com.qianfeng.entity.Source;

import java.sql.Connection;
import java.util.List;

public class SourceDaoImpl extends BaseDao implements SourceDao {
    @Override
    public void addSource(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public void deleteSource(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public Source getSource(Connection connection, String sql, Class<Source> clazz, Object... object) {
        return get(connection,sql,clazz,object);
    }

    @Override
    public List<Source> getSourceList(Connection connection, String sql, Class<Source> clazz, Object... object) {
        return getList(connection,sql,clazz,object);
    }

    @Override
    public Integer GetCount(Connection connection, String sql) {
        return getCount(connection,sql);
    }
}
