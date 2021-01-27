package com.qianfeng.controller;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.SubjectType;
import com.qianfeng.enums.MyEnums;
import com.qianfeng.exception.MyException;
import com.qianfeng.service.SubjectTypeService;
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
public class SubjectTypeController {
    @Autowired
    private SubjectTypeService subjectTypeService;

    @RequiresAuthentication
    @RequestMapping(value = "/addSubjectType",method = RequestMethod.POST)
    @ResponseBody
    public Result addSubjectType(SubjectType subjectType){

        Result result =null;
        try{
            result = subjectTypeService.addSubjectType(subjectType);
        }catch (Exception e){
            throw new MyException(MyEnums.ADD_ERROR);
        }
        return result;
    }

    @RequiresAuthentication
    @RequestMapping("/deleteSubjectType")
    @ResponseBody
    public Result deleteSubjectType(Integer subjectTypeId){

        Result result =null;
        try{
            result = subjectTypeService.deleteSubjectType(subjectTypeId);
        }catch (Exception e){
            throw new MyException(MyEnums.DELETE_ERROR);
        }
        return result;
    }

    @RequiresAuthentication
    @RequestMapping(value = "/updateSubjectType",method = RequestMethod.POST)
    @ResponseBody
    public Result updateSubjectType(SubjectType subjectType){

        Result result =null;
        try{
            result = subjectTypeService.updateSubjectType(subjectType);
        }catch (Exception e){
            throw new MyException(MyEnums.UPDATE_ERROR);
        }
        return result;
    }

    @RequiresAuthentication
    @RequestMapping("/getSubjectTypeList")
    @ResponseBody
    public Result getSubjectTypeList(Integer pageNo, Integer pageSize){

        Result result =null;
        try{
            result = subjectTypeService.getSubjectTypeList(pageNo,pageSize);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;
    }

    @RequiresAuthentication
    @RequestMapping("/getSubjectType")
    @ResponseBody
    public Result getSubjectType(Integer subjectTypeId){

        Result result =null;
        try{
            result = subjectTypeService.getSubjectType(subjectTypeId);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;
    }

}
