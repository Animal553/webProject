package com.qianfeng.service.Impl;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Class;
import com.qianfeng.service.ClassService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ClassServiceImplTest {

    @Autowired
    private ClassService classService;

    @Test
    public void addClass() {
        Class aClass = new Class();
        aClass.setClassName("测试班级2");
        aClass.setSubjectId(2);
        aClass.setClassCount(100);
        aClass.setClassLife(22);
        aClass.setClassAddress("南京");
        aClass.setCreateTime(new Date());

        Result result = classService.addClass(aClass);
        System.out.println(result);
    }

    @Test
    public void deleteClass() {
        classService.deleteClass(7);

    }

    @Test
    public void updateClass() {
        Class aClass = new Class();
        aClass.setClassId(7);
        aClass.setClassName("修改班级2");
        aClass.setSubjectId(2);
        aClass.setClassCount(100);
        aClass.setClassLife(22);
        aClass.setClassAddress("北京");
        aClass.setCreateTime(new Date());

        Result result = classService.addClass(aClass);
        System.out.println(result);
    }

    @Test
    public void getClassList() {
        Result classList = classService.getClassList(1, 2);
        System.out.println(classList);
    }

    @Test
    public void testGetClass() {
        Result aClass = classService.getClass(6);
        System.out.println(aClass);

    }
}