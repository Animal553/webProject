package com.qianfeng.service.Impl;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Subject;
import com.qianfeng.entity.SubjectType;
import com.qianfeng.service.SubjectTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SubjectTypeServiceImplTest {

    @Autowired
    private SubjectTypeService subjectTypeService;

    @Test
    public void addSubjectType() {
        SubjectType subjectType = new SubjectType();
        subjectType.setSubjectTypeName("测试补习班2");

        subjectTypeService.addSubjectType(subjectType);

    }

    @Test
    public void deleteSubjectType() {

        Result result = subjectTypeService.deleteSubjectType(7);
        System.out.println(result);

    }

    @Test
    public void updateSubjectType() {

        SubjectType subjectType = new SubjectType();
        subjectType.setSubjectTypeId(6);
        subjectType.setSubjectTypeName("修改补习班");
        Result result = subjectTypeService.updateSubjectType(subjectType);
        System.out.println(result);

    }

    @Test
    public void getSubjectTypeList() {
        Result subjectTypeList = subjectTypeService.getSubjectTypeList(1, 2);
        System.out.println(subjectTypeList);

    }

    @Test
    public void getSubjectType() {
        Result subjectType = subjectTypeService.getSubjectType(6);
        System.out.println(subjectType);
    }
}