package com.qianfeng.dao;

import com.qianfeng.entity.Department;

import java.sql.Connection;
import java.util.List;

public interface DepartmentDao {
    //查找一个部门
    Department getDepartment(Connection connection, String sql, Class<Department> clazz, Object... objects);

    //查找全部的部门
    List<Department> getDepartmentList(Connection connection, String sql, Class<Department> clazz, Object... objects);

    Integer GetCount(Connection connection, String sql);

    void addDepartment(Connection connection, String sql, Object... object);

    void deleteDepartment(Connection connection, String sql, Object... object);
}
