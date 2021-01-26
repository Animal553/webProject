package com.qianfeng.controller;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Role;
import com.qianfeng.enums.MyEnums;
import com.qianfeng.exception.MyException;
import com.qianfeng.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiresRoles(value = {"manager"})
@CrossOrigin
@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequiresRoles(value = {"manager"})
    @RequestMapping(value = "/add_role",method = RequestMethod.POST)
    @ResponseBody
    public Result add_role(Role role){

        Result result =null;
        try{
            result = roleService.add_role(role);
        }
        catch (Exception e){
            throw new MyException(MyEnums.ADD_ERROR);
        }
        return result;
    }

    @RequiresRoles(value = {"manager"})
    @RequestMapping("/delete_role")
    @ResponseBody
    public Result delete_role(Integer roleId){

        Result result=null;
        try{
            result = roleService.delete_role(roleId);
        }catch (Exception e){
            throw new MyException(MyEnums.DELETE_ERROR);
        }
        return result;
    }

    @RequiresRoles(value = {"manager"})
    @RequestMapping(value = "/update_role",method = RequestMethod.POST)
    @ResponseBody
    public Result delete_role(Role role){
        Result result=null;
        try{
            result = roleService.update_role(role);
        }catch (Exception e){
            throw new MyException(MyEnums.UPDATE_ERROR);
        }
        return result;
    }

    @RequiresRoles(value = {"manager"})
    @RequestMapping("/getRolePage")
    @ResponseBody
    public Result getRolePage(Integer pageNo,Integer pageSize){

        Result result=null;
        try{
            result = roleService.get_roleList(pageNo, pageSize);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;

    }

    @RequiresRoles(value = {"manager"})
    @RequestMapping("/getRoleByRoleId")
    @ResponseBody
    public Result getRolePage(Integer roleId){

        Result result=null;
        try{
            result = roleService.get_role(roleId);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;
    }
}
