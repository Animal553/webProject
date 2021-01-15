package com.qianfeng.dao;

import com.qianfeng.entity.Source;

import java.sql.Connection;
import java.util.List;

public interface SourceDao {
    void addSource(Connection connection, String sql, Object... object);
    void deleteSource(Connection connection, String sql, Object... object);
    Source getSource(Connection connection, String sql, Class<Source> clazz, Object... object);
    List<Source> getSourceList(Connection connection, String sql, Class<Source> clazz, Object... object);
    Integer GetCount(Connection connection, String sql);
}
