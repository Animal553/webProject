package com.qianfeng.service.Impl;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Employee;
import com.qianfeng.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void addEmployee() {

        Employee employee = new Employee();
        employee.setEmpName("测试添加员工1");
        employee.setDeptId(3);
        employee.setGender("男");
        employee.setPhone("18888888888");
        employee.setQq("456789");
        employee.setEmail("1@1.com");
        employee.setHireTime(new Date());
        employee.setEmpPassword("123456");
        employee.setRoleId(10000);
        Result result = employeeService.addEmployee(employee);
        System.out.println(result);
    }

    @Test
    public void updateEmployee() {
        Employee employee = new Employee();
        employee.setEmpId(25);
        employee.setEmpName("测试修改员工");
        employee.setDeptId(3);
        employee.setGender("男");
        employee.setPhone("1");
        employee.setQq("4567890");
        employee.setEmail("1@1.com");
        employee.setHireTime(new Date());
        employee.setEmpPassword("123456");
        employee.setRoleId(10000);
        Result result = employeeService.updateEmployee(employee);
        System.out.println(result);
    }

    @Test
    public void deleteEmployee() {
        employeeService.deleteEmployee(24);
    }

    @Test
    public void getEmployeeList() {
        Result employeeList = employeeService.getEmployeeList(1, 2);
        System.out.println(employeeList);
    }

    @Test
    public void get_EmployeeById() {
        Result employeeById = employeeService.get_EmployeeById(25);
        System.out.println(employeeById);
    }
}