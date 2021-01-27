package com.qianfeng.service;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Department;


public interface DepartmentService {
    Result getDepartmentList(Integer pageNo, Integer pageSize);
    Result getDepartment(Integer deptId);
    Result addDepartment(Department department);
    Result deleteDepartment(Integer deptId);
    Result updateDepartment(Department department);
}
