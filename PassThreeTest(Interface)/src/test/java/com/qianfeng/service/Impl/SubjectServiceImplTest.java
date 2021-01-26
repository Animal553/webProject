package com.qianfeng.service.Impl;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Subject;
import com.qianfeng.service.SubjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SubjectServiceImplTest {
    @Autowired
    private SubjectService subjectService;

    @Test
    public void addSubject() {
        Subject subject = new Subject();
        subject.setSubjectName("测试课程名2");
        subject.setSubjectLife(22);
        subject.setCreateTime(new Date());
        subject.setSubjectTypeId(2);

        Result result = subjectService.addSubject(subject);
        System.out.println(result);
    }

    @Test
    public void deleteSubject() {

        subjectService.deleteSubject(10);
    }

    @Test
    public void updateSubject() {

        Subject subject = new Subject();
        subject.setSubjectId(9);
        subject.setSubjectName("修改课程名2");
        subject.setSubjectLife(22);
        subject.setCreateTime(new Date());
        subject.setSubjectTypeId(4);

        Result result = subjectService.updateSubject(subject);
        System.out.println(result);

    }

    @Test
    public void getSubjectList() {

        Result subjectList = subjectService.getSubjectList(1, 2);
        System.out.println(subjectList);

    }

    @Test
    public void getSubject() {
        Result subject = subjectService.getSubject(9);
        System.out.println(subject);
    }
}