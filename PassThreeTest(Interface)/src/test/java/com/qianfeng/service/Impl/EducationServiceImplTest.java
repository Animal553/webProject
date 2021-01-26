package com.qianfeng.service.Impl;

import com.qianfeng.Page.Result;
import com.qianfeng.service.EducationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EducationServiceImplTest {

    @Autowired
    private EducationService educationService;


    @Test
    public void getEducationList() {
        Result educationList = educationService.getEducationList(1, 2);
        System.out.println(educationList);
    }

    @Test
    public void getEducation() {
        Result education = educationService.getEducation(6);
        System.out.println(education);
    }
}