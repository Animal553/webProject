package com.qianfeng.controller;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Employee;
import com.qianfeng.enums.MyEnums;
import com.qianfeng.exception.MyException;
import com.qianfeng.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiresRoles(value = {"manager"})
@CrossOrigin
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequiresRoles(value = {"manager"})
    @RequestMapping("/getEmployeePage")
    @ResponseBody
    public Result getEmployeePage(Integer pageNo, Integer pageSize){

        Result result=null;
        try{
            result = employeeService.getEmployeeList(pageNo, pageSize);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;
    }

    @RequiresRoles(value = {"manager"})
    @RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
    @ResponseBody
    public Result addEmployee(Employee employee){

        Result result=null;
        try{
            result = employeeService.addEmployee(employee);
        }catch (Exception e){
            throw new MyException(MyEnums.ADD_ERROR);
        }
        return result;
    }

    @RequiresRoles(value = {"manager"})
    @RequestMapping("/deleteEmployee")
    @ResponseBody
    public Result deleteEmployee(Integer empId){

        Result result=null;
        try{
            result = employeeService.deleteEmployee(empId);
        }catch (Exception e){
            throw new MyException(MyEnums.DELETE_ERROR);
        }
        return result;
    }

    @RequiresRoles(value = {"manager"})
    @RequestMapping(value = "/updateEmployee",method = RequestMethod.POST)
    @ResponseBody
    public Result updateEmployee(Employee employee ){

        Result result=null;
        try{
            result = employeeService.updateEmployee(employee);
        }catch (Exception e){
            throw new MyException(MyEnums.UPDATE_ERROR);
        }
        return result;
    }

    @RequiresRoles(value = {"manager"})
    @RequestMapping("/getEmployeeByEmpId")
    @ResponseBody
    public Result getEmployeeByEmpId(Integer empId ){

        Result result=null;
        try{
            result = employeeService.get_EmployeeById(empId);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(Integer empId,String empPassword){
        System.out.println("========"+empId+"======="+empPassword);
        Result result = new Result();
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(empId), empPassword);

        token.setRememberMe(true);
        try {
            subject.login(token);
        }catch (UnknownAccountException uae){
            result.setCode(503);
            result.setMsg("用户名不存在");
            System.out.println("用户名不存在:"+uae.getMessage());
            return result;
        }catch (IncorrectCredentialsException ice){
            result.setCode(504);
            result.setMsg("密码错误");
            System.out.println("密码错误:"+ice.getMessage());
            return result;
        }catch (AuthenticationException ae){
            result.setCode(505);
            result.setMsg("其他异常");
            System.out.println("其他异常:"+ae.getMessage());
            return result;
        }
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }
}
