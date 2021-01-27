package com.qianfeng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.Page.Page;
import com.qianfeng.Page.Result;
import com.qianfeng.entity.Employee;
import com.qianfeng.mapper.EmployeeMapper;
import com.qianfeng.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Transactional
    @Override
    public Result addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }

    @Transactional
    @Override
    public Result updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }

    @Transactional
    @Override
    public Result deleteEmployee(Integer empId) {
        employeeMapper.deleteEmployee(empId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }

    @Transactional
    @Override
    public Result getEmployeeList(Integer pageNo, Integer pageSize) {

        if (pageNo==null){
            pageNo=1;
        }
        if (pageSize==null){
            pageSize=4;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<Employee> employeeList = employeeMapper.getEmployeeList();
        PageInfo<Employee> info = new PageInfo<>(employeeList);

        Page page = new Page();
        page.setHasNext(info.isHasNextPage());
        page.setHasPre(info.isHasPreviousPage());
        page.setPageNo(info.getPageNum());
        page.setPageSize(info.getPageSize());
        page.setPageCount(info.getPages());
        page.setPage(info.getList());

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(page);

        return result;
    }

    @Transactional
    @Override
    public Result get_EmployeeById(Integer empId) {
        Employee employee = employeeMapper.get_EmployeeById(empId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(employee);

        return result;
    }
}
