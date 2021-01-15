package com.qianfeng.dao;

import com.qianfeng.entity.Employee;

import java.sql.Connection;
import java.util.List;

public interface EmployeeDao {
    //员工的增删改查
    //增
    void addEmployee(Connection connection, String sql, Object... object);

    //删
    void deleteEmployee(Connection connection, String sql, Object... object);

    //改
    void updateEmployee(Connection connection, String sql, Object... object);

    //查
    Employee getEmployee(Connection connection, String sql, Class<Employee> clazz, Object... object);

    //查找多个
    List<Employee> getEmployeeList(Connection connection, String sql, Class<Employee> clazz, Object... object);

    //查找员工人数
    Integer GetCount(Connection connection, String sql);

}
