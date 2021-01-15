package com.qianfeng.dao.Impl;

import com.qianfeng.dao.BaseDao;
import com.qianfeng.dao.LevelDao;
import com.qianfeng.entity.Level;

import java.sql.Connection;
import java.util.List;

public class LevelDaoImpl extends BaseDao implements LevelDao {
    @Override
    public void addLevel(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public void deleteLevel(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public Level getLevel(Connection connection, String sql, Class<Level> clazz, Object... object) {
        return get(connection,sql,clazz,object);
    }

    @Override
    public List<Level> getLevelList(Connection connection, String sql, Class<Level> clazz, Object... object) {
        return getList(connection,sql,clazz,object);
    }

    @Override
    public Integer GetCount(Connection connection, String sql) {
        return getCount(connection,sql);
    }
}
