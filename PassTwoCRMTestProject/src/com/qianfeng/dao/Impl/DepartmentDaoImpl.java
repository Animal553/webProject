package com.qianfeng.dao.Impl;

import com.qianfeng.dao.BaseDao;
import com.qianfeng.dao.DepartmentDao;
import com.qianfeng.entity.Department;

import java.sql.Connection;
import java.util.List;

public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {
    @Override
    public Department getDepartment(Connection connection, String sql, Class<Department> clazz, Object... objects) {
        return get(connection,sql,Department.class,objects);
    }

    @Override
    public List<Department> getDepartmentList(Connection connection, String sql, Class<Department> clazz, Object... objects) {
        return getList(connection,sql,Department.class,objects);
    }

    @Override
    public Integer GetCount(Connection connection, String sql) {
        return getCount(connection,sql);
    }

    @Override
    public void addDepartment(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public void deleteDepartment(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }
}
