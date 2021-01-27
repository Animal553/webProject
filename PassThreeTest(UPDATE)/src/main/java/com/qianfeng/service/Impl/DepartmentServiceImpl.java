package com.qianfeng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.Page.Page;
import com.qianfeng.Page.Result;
import com.qianfeng.entity.Department;
import com.qianfeng.mapper.DepartmentMapper;
import com.qianfeng.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Transactional
    @Override
    public Result getDepartmentList(Integer pageNo, Integer pageSize) {

        if (pageNo==null){
            pageNo=1;
        }
        if (pageSize==null){
            pageSize=4;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<Department> departmentList = departmentMapper.getDepartmentList();
        PageInfo<Department> info = new PageInfo<>(departmentList);

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
    public Result getDepartment(Integer deptId) {
        Department department = departmentMapper.getDepartment(deptId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(department);

        return result;
    }

    @Transactional
    @Override
    public Result addDepartment(Department department) {
        departmentMapper.addDepartment(department);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }

    @Transactional
    @Override
    public Result deleteDepartment(Integer deptId) {
        departmentMapper.deleteDepartment(deptId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }

    @Transactional
    @Override
    public Result updateDepartment(Department department) {
        departmentMapper.updateDepartment(department);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }
}
