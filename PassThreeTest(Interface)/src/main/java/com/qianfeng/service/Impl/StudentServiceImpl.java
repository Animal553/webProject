package com.qianfeng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.Page.Page;
import com.qianfeng.Page.Result;
import com.qianfeng.entity.Student;
import com.qianfeng.mapper.StudentMapper;
import com.qianfeng.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    @Override
    public Result add_student(Student student) {
        studentMapper.add_student(student);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result update_student(Student student) {
        studentMapper.update_student(student);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result delete_student(Integer studentId) {
        studentMapper.delete_student(studentId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result getStudentList(Integer pageNo, Integer pageSize) {

        if (pageNo==null){
            pageNo=1;
        }
        if (pageSize==null){
            pageSize=4;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<Student> studentList = studentMapper.getStudentList();
        PageInfo<Student> info = new PageInfo<>(studentList);

        Page page = new Page();
        page.setHasNext(info.isHasNextPage());
        page.setHasPre(info.isHasPreviousPage());
        page.setPageNo(info.getPageNum());
        page.setPageSize(info.getPageSize());
        page.setPageCount(info.getPages());
        page.setPage(info.getList());

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(page);

        return result;
    }

    @Transactional
    @Override
    public Result get_studentById(Integer studentId) {
        Student studentById = studentMapper.get_studentById(studentId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(studentById);

        return result;
    }
}
