package com.qianfeng.controller;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Attendance;
import com.qianfeng.enums.MyEnums;
import com.qianfeng.exception.MyException;
import com.qianfeng.service.AttendanceService;
import org.apache.shiro.authz.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
//@RequiresAuthentication 认证注解
@RequiresRoles(value = {"manager"}) //角色
//@RequiresPermissions(value = {"delete"},logical = Logical.OR) 权限
//@RequiresUser 记住我
@Controller
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;


    @RequiresRoles(value = {"manager"})
    @RequestMapping(value = "/add_attendance",method = RequestMethod.POST)
    @ResponseBody
    public Result add_attendance(Attendance attendance){

        Result result = null;
        try{
            result = attendanceService.add_attendance(attendance);
        }catch (Exception e){
            throw new MyException(MyEnums.ADD_ERROR);
        }
        return result;

    }

    @RequiresRoles(value = {"manager"})
    @RequestMapping(value = "/update_attendance",method = RequestMethod.POST)
    @ResponseBody
    public Result update_attendance(Attendance attendance){

        Result result = null;
        try{
            result = attendanceService.update_attendance(attendance);
        }catch (Exception e){
            throw new MyException(MyEnums.UPDATE_ERROR);
        }
        return result;

    }

    @RequiresRoles(value = {"manager"})
    @RequestMapping(value = "/delete_attendance")
    @ResponseBody
    public Result delete_attendance(Integer attendanceId){

        Result result = null;
        try{
            result = attendanceService.delete_attendance(attendanceId);
        }catch (Exception e){
            throw new MyException(MyEnums.DELETE_ERROR);
        }
        return result;

    }

    @RequiresRoles(value = {"y架构师"})
    @RequestMapping(value = "/getAttendancePage")
    @ResponseBody
    public Result getAttendancePage(Integer pageNo,Integer pageSize){

        Result result = null;
        try{
            result = attendanceService.getAttendanceList(pageNo,pageSize);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;

    }

    @RequiresRoles(value = {"manager"})
    @RequestMapping(value = "/get_attendanceById")
    @ResponseBody
    public Result get_attendanceById(Integer attendanceId){

        Result result = null;
        try{
            result = attendanceService.get_attendanceById(attendanceId);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;

    }
}
