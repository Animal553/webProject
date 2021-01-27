package com.qianfeng.service.Impl;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Student;
import com.qianfeng.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void add_student() {

        Student student = new Student();
        student.setStudentName("lilei1");
        student.setClassId(4);
        student.setStuGender("男");
        student.setStuPhone("18888888");
        student.setStuQq("22222222");
        student.setStuEmail("1@1.com");
        student.setGraduateSchool("安徽师范大学");
        student.setEducationId(4);
        student.setCreateTime(new Date());
        student.setInTime(new Date());
        student.setEmpId(21);
        student.setCardNum("456456456");
        Result result = studentService.add_student(student);
        System.out.println(result);
    }

    @Test
    public void update_student() {

        Student student = new Student();
        student.setStudentId(33);
        student.setStudentName("lilei1");
        student.setClassId(4);
        student.setStuGender("男");
        student.setStuPhone("18888888");
        student.setStuQq("22222222");
        student.setStuEmail("2@2.com");
        student.setGraduateSchool("南京示范大学");
        student.setEducationId(4);
        student.setCreateTime(new Date());
        student.setInTime(new Date());
        student.setEmpId(21);
        student.setCardNum("4");
        Result result = studentService.update_student(student);
        System.out.println(result);

    }

    @Test
    public void delete_student() {
        studentService.delete_student(34);
    }

    @Test
    public void getStudentList() {
        Result studentList = studentService.getStudentList(1, 2);
        System.out.println(studentList);
    }

    @Test
    public void get_studentById() {
        Result studentById = studentService.get_studentById(33);
        System.out.println(studentById);
    }
}