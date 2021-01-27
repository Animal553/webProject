package com.qianfeng.mapper;

import com.qianfeng.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    List<Department> getDepartmentList();
    Department getDepartment(Integer deptId);
    void addDepartment(Department department);
    void deleteDepartment(Integer deptId);
    void updateDepartment(Department department);
}
