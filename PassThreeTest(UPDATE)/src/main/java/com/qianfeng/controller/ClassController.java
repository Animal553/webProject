package com.qianfeng.controller;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Class;
import com.qianfeng.enums.MyEnums;
import com.qianfeng.exception.MyException;
import com.qianfeng.service.ClassService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiresRoles(value = {"teacher"})
@CrossOrigin
@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    @RequiresRoles(value = {"teacher"})
    @RequestMapping("/getClassRoomPage")
    @ResponseBody
    public Result getClassRoomPage(Integer pageNo,Integer pageSize){

        Result result=null;
        try{
            result = classService.getClassList(pageNo, pageSize);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }

        return result;
    }


    @RequiresRoles(value = {"teacher"})
    @RequestMapping(value = "/addClassRoom",method = RequestMethod.POST)
    @ResponseBody
    public Result addClassRoomPage(Class clazz){

        Result result=null;
        try{
            result = classService.addClass(clazz);
        }catch (Exception e){
            throw new MyException(MyEnums.ADD_ERROR);
        }

        return result;
    }

    @RequiresRoles(value = {"teacher"})
    @RequestMapping("/deleteClass")
    @ResponseBody
    public Result deleteClass(Integer classId){

        Result result=null;
        try{
            result = classService.deleteClass(classId);
        }catch (Exception e){
            throw new MyException(MyEnums.DELETE_ERROR);
        }

        return result;
    }

    @RequiresRoles(value = {"teacher"})
    @RequestMapping(value = "/updateClassRoom",method = RequestMethod.POST)
    @ResponseBody
    public Result updateClassRoom(Class clazz){

        Result result=null;
        try{
            result = classService.updateClass(clazz);
        }catch (Exception e){
            throw new MyException(MyEnums.UPDATE_ERROR);
        }

        return result;
    }

    @RequiresRoles(value = {"teacher"})
    @RequestMapping("/getClassRoomByClassId")
    @ResponseBody
    public Result getClassRoomByClassId(Integer classId){

        Result result=null;
        try{
            result = classService.getClass(classId);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }

        return result;
    }

}
