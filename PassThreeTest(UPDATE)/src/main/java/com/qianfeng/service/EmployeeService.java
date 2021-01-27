package com.qianfeng.service;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Employee;


public interface EmployeeService {
    Result addEmployee(Employee employee);
    Result updateEmployee(Employee employee);
    Result deleteEmployee(Integer empId);
    Result getEmployeeList(Integer pageNo,Integer pageSize);
    Result get_EmployeeById(Integer empId);
}
