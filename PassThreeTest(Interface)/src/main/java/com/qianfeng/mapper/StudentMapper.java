package com.qianfeng.mapper;

import com.qianfeng.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    void add_student(Student student);
    void update_student(Student student);
    void delete_student(Integer studentId);
    List<Student> getStudentList();
    Student get_studentById(Integer studentId);
}
