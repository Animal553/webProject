package com.qianfeng.service.Impl;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Department;
import com.qianfeng.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void getDepartmentList() {
        Result result = departmentService.getDepartmentList(1, 2);
        System.out.println(result);

    }

    @Test
    public void getDepartment() {
        Result result = departmentService.getDepartment(1);
        System.out.println(result);
    }

    @Test
    public void addDepartment() {

        Department department = new Department();
        department.setDeptName("测试添加部门2");
        department.setCreateTime(new Date());

        departmentService.addDepartment(department);
    }

    @Test
    public void deleteDepartment() {

        Result result = departmentService.deleteDepartment(9);
        System.out.println(result);
    }

    @Test
    public void updateDepartment() {
        Department department = new Department();
        department.setDeptId(8);
        department.setDeptName("测试修改部门");
        department.setCreateTime(new Date());
        departmentService.updateDepartment(department);
    }
}