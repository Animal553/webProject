package com.qianfeng.controller;

import com.qianfeng.Page.Result;
import com.qianfeng.enums.MyEnums;
import com.qianfeng.exception.MyException;
import com.qianfeng.service.EducationService;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiresAuthentication
@CrossOrigin
@Controller
public class EducationController {

    @Autowired
    private EducationService educationService;

    @RequiresAuthentication
    @RequestMapping("/getEducationList")
    @ResponseBody
    public Result getEducationList(Integer pageNo, Integer pageSize){

        Result result =null;
        try {
            result = educationService.getEducationList(pageNo, pageSize);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;

    }

    @RequiresAuthentication
    @RequestMapping("/getEducation")
    @ResponseBody
    public Result getEducation(Integer educationId){

        Result result =null;
        try {
            result = educationService.getEducation(educationId);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;
    }

}
