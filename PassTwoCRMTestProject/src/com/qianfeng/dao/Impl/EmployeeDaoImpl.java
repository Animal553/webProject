package com.qianfeng.dao.Impl;

import com.qianfeng.dao.BaseDao;
import com.qianfeng.dao.EmployeeDao;
import com.qianfeng.entity.Employee;

import java.sql.Connection;
import java.util.List;

public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {

    @Override
    public void addEmployee(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public void deleteEmployee(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public void updateEmployee(Connection connection, String sql, Object... object) {
        update(connection,sql,object);
    }

    @Override
    public Employee getEmployee(Connection connection, String sql, Class<Employee> clazz, Object... object) {
        return get(connection,sql,clazz,object);
    }

    @Override
    public List<Employee> getEmployeeList(Connection connection, String sql, Class<Employee> clazz, Object... object) {
        return getList(connection,sql,clazz,object);
    }

    @Override
    public Integer GetCount(Connection connection, String sql) {
        return getCount(connection,sql);
    }
}
