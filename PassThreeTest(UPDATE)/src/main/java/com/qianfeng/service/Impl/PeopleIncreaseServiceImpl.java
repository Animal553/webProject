package com.qianfeng.service.Impl;

import com.qianfeng.entity.PeopleIncrease;
import com.qianfeng.mapper.PeopleIncreaseMapper;
import com.qianfeng.service.PeopleIncreaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PeopleIncreaseServiceImpl implements PeopleIncreaseService {

    @Autowired
    private PeopleIncreaseMapper peopleIncreaseMapper;

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease1() {
        PeopleIncrease peopleIncrease1 = peopleIncreaseMapper.getPeopleIncrease1();
        peopleIncrease1.setName("周一");
        peopleIncrease1.setDrillDown("第一周新增");
        return peopleIncrease1;
    }

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease2() {
        PeopleIncrease peopleIncrease2 = peopleIncreaseMapper.getPeopleIncrease2();
        peopleIncrease2.setName("周二");
        peopleIncrease2.setDrillDown("第二周新增");
        return peopleIncrease2;
    }

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease3() {
        PeopleIncrease peopleIncrease3 = peopleIncreaseMapper.getPeopleIncrease3();
        peopleIncrease3.setName("周三");
        peopleIncrease3.setDrillDown("第三周新增");
        return peopleIncrease3;
    }

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease4() {
        PeopleIncrease peopleIncrease4 = peopleIncreaseMapper.getPeopleIncrease4();
        peopleIncrease4.setName("周四");
        peopleIncrease4.setDrillDown("第四周新增");
        return peopleIncrease4;
    }

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease5() {
        PeopleIncrease peopleIncrease5 = peopleIncreaseMapper.getPeopleIncrease5();
        peopleIncrease5.setName("周五");
        peopleIncrease5.setDrillDown("第五周新增");
        return peopleIncrease5;
    }

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease6() {
        PeopleIncrease peopleIncrease6 = peopleIncreaseMapper.getPeopleIncrease6();
        peopleIncrease6.setName("周六");
        peopleIncrease6.setDrillDown("第六周新增");
        return peopleIncrease6;
    }

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease7() {
        PeopleIncrease peopleIncrease7 = peopleIncreaseMapper.getPeopleIncrease7();
        peopleIncrease7.setName("周日");
        peopleIncrease7.setDrillDown("第日周新增");
        return peopleIncrease7;
    }

}
