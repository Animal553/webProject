package com.qianfeng.dao;
import com.qianfeng.entity.Level;
import java.sql.Connection;
import java.util.List;

public interface LevelDao {
    void addLevel(Connection connection, String sql, Object... object);
    void deleteLevel(Connection connection, String sql, Object... object);

    Level getLevel(Connection connection, String sql, Class<Level> clazz, Object... object);
    List<Level> getLevelList(Connection connection, String sql, Class<Level> clazz, Object... object);
    Integer GetCount(Connection connection, String sql);
}
