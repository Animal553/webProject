package com.qianfeng.controller;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Subject;
import com.qianfeng.enums.MyEnums;
import com.qianfeng.exception.MyException;
import com.qianfeng.service.SubjectService;
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
public class SubjectController {
    @Autowired
    private SubjectService subjectService;


    @RequiresAuthentication
    @RequestMapping("/getSubjectPage")
    @ResponseBody
    public Result getSubjectPage(Integer pageNo, Integer pageSize){

        Result result=null;

        try {
            result = subjectService.getSubjectList(pageNo, pageSize);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }

        return result;
    }

    @RequiresAuthentication
    @RequestMapping(value = "/addSubject",method = RequestMethod.POST)
    @ResponseBody
    public Result addSubject(Subject subject){

        Result result=null;

        try {
            result = subjectService.addSubject(subject);
        }catch (Exception e){
            throw new MyException(MyEnums.ADD_ERROR);
        }

        return result;
    }

    @RequiresAuthentication
    @RequestMapping("/deleteSubject")
    @ResponseBody
    public Result deleteSubject(Integer subjectId){

        Result result=null;

        try {
            result = subjectService.deleteSubject(subjectId);
        }catch (Exception e){
            throw new MyException(MyEnums.DELETE_ERROR);
        }

        return result;
    }

    @RequiresAuthentication
    @RequestMapping(value = "/updateSubject",method = RequestMethod.POST)
    @ResponseBody
    public Result updateSubject(Subject subject){

        Result result=null;

        try {
            result = subjectService.updateSubject(subject);
        }catch (Exception e){
            throw new MyException(MyEnums.UPDATE_ERROR);
        }

        return result;
    }

    @RequiresAuthentication
    @RequestMapping("/getSubjectById")
    @ResponseBody
    public Result updateSubject(Integer subjectId){

        Result result=null;

        try {
            result = subjectService.getSubject(subjectId);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }
        return result;
    }

}
