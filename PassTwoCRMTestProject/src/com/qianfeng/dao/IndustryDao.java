package com.qianfeng.dao;
import com.qianfeng.entity.Industry;
import java.sql.Connection;
import java.util.List;

public interface IndustryDao {
    void addIndustry(Connection connection, String sql, Object... object);
    void deleteIndustry(Connection connection, String sql, Object... object);

    Industry getIndustry(Connection connection, String sql, Class<Industry> clazz, Object... object);
    List<Industry> getIndustryList(Connection connection, String sql, Class<Industry> clazz, Object... object);
    Integer GetCount(Connection connection, String sql);
}
