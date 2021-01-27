package com.qianfeng.service.Impl;

import com.qianfeng.Page.Result;
import com.qianfeng.service.WorkTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class WorkTypeServiceImplTest {

    @Autowired
    private WorkTypeService workTypeService;
    @Test
    public void getWorkTypeList() {
        Result workTypeList = workTypeService.getWorkTypeList();

        System.out.println(workTypeList);
    }
}