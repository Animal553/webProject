package com.qianfeng.controller;

import com.qianfeng.Page.Result;
import com.qianfeng.enums.MyEnums;
import com.qianfeng.exception.MyException;
import com.qianfeng.service.WorkTypeService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiresAuthentication
@CrossOrigin
@Controller
public class WorkTypeController {
    @Autowired
    WorkTypeService workTypeService;

    @RequiresAuthentication
    @RequestMapping("/getWorkTypes")
    @ResponseBody
    public Result getWorkTypes(){

        Result result =null;
        try{
            result = workTypeService.getWorkTypeList();
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;
    }
}
