package com.qianfeng.service;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Student;


public interface StudentService {

    Result add_student(Student student);
    Result update_student(Student student);
    Result delete_student(Integer studentId);
    Result getStudentList(Integer pageNo,Integer pageSize);
    Result get_studentById(Integer studentId);
}
