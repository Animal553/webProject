package com.qianfeng.controller;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Department;
import com.qianfeng.enums.MyEnums;
import com.qianfeng.exception.MyException;
import com.qianfeng.service.DepartmentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RequiresAuthentication
@CrossOrigin
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequiresAuthentication
    @RequestMapping("/getDepartmentPageJson")
    @ResponseBody
    public Result getDepartmentPageJson(Integer pageNo,Integer pageSize){

        Result result=null;

        try{
            result = departmentService.getDepartmentList(pageNo, pageSize);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;

    }

    @RequiresAuthentication
    @RequestMapping(value = "/add_department",method = RequestMethod.POST)
    @ResponseBody
    public Result add_department(Department department){

        Result result=null;

        try{
            result = departmentService.addDepartment(department);
        }catch (Exception e){
            throw new MyException(MyEnums.ADD_ERROR);
        }
        return result;

    }


    @RequiresAuthentication
    @RequestMapping(value = "/update_department",method = RequestMethod.POST)
    @ResponseBody
    public Result update_department(Department department){

        Result result=null;

        try{
            result = departmentService.updateDepartment(department);
        }catch (Exception e){
            throw new MyException(MyEnums.UPDATE_ERROR);
        }
        return result;

    }

    @RequiresAuthentication
    @RequestMapping("/delete_department")
    @ResponseBody
    public Result delete_department(Integer deptId){

        Result result=null;

        try{
            result = departmentService.deleteDepartment(deptId);
        }catch (Exception e){
            throw new MyException(MyEnums.DELETE_ERROR);
        }
        return result;

    }

    @RequiresAuthentication
    @RequestMapping("/getDepartmentByDeptId")
    @ResponseBody
    public Result getDepartmentByDeptId(Integer deptId){

        Result result=null;

        try{
            result = departmentService.getDepartment(deptId);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;

    }
}
