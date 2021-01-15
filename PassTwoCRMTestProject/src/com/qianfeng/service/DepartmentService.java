package com.qianfeng.service;

import com.qianfeng.dto.Page;
import com.qianfeng.entity.Department;

import java.util.List;

public interface DepartmentService {
    //部门的查询
    Department selectDepartment(Integer deptId);

    //查询所有的部门
    List<Department> selectDepartmentList();

    Page getDepartmentPage(Integer pageNo, Integer pageSize);

    void addDepartment(Department department);

    void deleteDepartment(Integer deptId);

}
