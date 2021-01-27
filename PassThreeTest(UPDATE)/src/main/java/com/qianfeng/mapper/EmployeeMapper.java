package com.qianfeng.mapper;

import com.qianfeng.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  EmployeeMapper {
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(Integer empId);
    List<Employee> getEmployeeList();
    Employee get_EmployeeById(Integer empId);
}
