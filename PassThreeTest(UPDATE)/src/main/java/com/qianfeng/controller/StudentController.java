package com.qianfeng.controller;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Student;
import com.qianfeng.enums.MyEnums;
import com.qianfeng.exception.MyException;
import com.qianfeng.service.StudentService;
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
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequiresRoles(value = {"teacher"})
    @RequestMapping("/getStudentPage")
    @ResponseBody
    public Result getStudentPage(Integer pageNo,Integer pageSize){
        Result result =null;
        try{
            result = studentService.getStudentList(pageNo, pageSize);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }

        return result;
    }

    @RequiresRoles(value = {"teacher"})
    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    @ResponseBody
    public Result addStudent(Student student){
        Result result =null;
        try{
            result = studentService.add_student(student);
        }catch (Exception e){
            throw new MyException(MyEnums.ADD_ERROR);
        }

        return result;
    }

    @RequiresRoles(value = {"teacher"})
    @RequestMapping("/deleteStudent")
    @ResponseBody
    public Result deleteStudent(Integer studentId){
        Result result =null;
        try{
            result = studentService.delete_student(studentId);
        }catch (Exception e){
            throw new MyException(MyEnums.DELETE_ERROR);
        }

        return result;
    }

    @RequiresRoles(value = {"teacher"})
    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST)
    @ResponseBody
    public Result updateStudent(Student student){
        Result result =null;
        try{
            result = studentService.update_student(student);
        }catch (Exception e){
            throw new MyException(MyEnums.UPDATE_ERROR);
        }

        return result;
    }

    @RequiresRoles(value = {"teacher"})
    @RequestMapping("/getStudentByStuId")
    @ResponseBody
    public Result getStudentByStuId(Integer studentId){
        Result result =null;
        try{
            result = studentService.get_studentById(studentId);
        }catch (Exception e){
            throw new MyException(MyEnums.SELECT_ERROR);
        }

        return result;
    }

}
