package com.qianfeng.service;

import com.qianfeng.dto.Page;
import com.qianfeng.entity.Customer;
import com.qianfeng.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);
    void deleteEmployee(Integer empId);
    void updateEmployee(Employee employee);
    Employee selectEmployee(Integer empId);
    Employee selectEmail(String email);
    List<Employee> selectEmployeeList();
    Integer employeeCount();
    Page getEmployeePage(Integer pageNo, Integer pageSize);
    List<Employee> selectEmployeeList(String name);



    void updateEmployeeEmail(Integer empId , String empName , String email , String info);
    void updateEmployeePassword(Integer empId , String password);
    void updateEmployeeHead(Integer empId , String portrait);
}
