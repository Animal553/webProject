package com.qianfeng.service.Impl;

import com.qianfeng.entity.PeopleIncrease;
import com.qianfeng.mapper.PeopleIncreaseMonthMapper;
import com.qianfeng.service.PeopleIncreaseMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeopleIncreaseMonthServiceImpl implements PeopleIncreaseMonthService {
    @Autowired
    private PeopleIncreaseMonthMapper peopleIncreaseMonthMapper;

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease1() {
        PeopleIncrease peopleIncrease1 = peopleIncreaseMonthMapper.getPeopleIncrease1();
        peopleIncrease1.setName("1月");
        return peopleIncrease1;
    }

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease2() {
        PeopleIncrease peopleIncrease2 = peopleIncreaseMonthMapper.getPeopleIncrease2();
        peopleIncrease2.setName("2月");
        return peopleIncrease2;
    }

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease3() {
        PeopleIncrease peopleIncrease3 = peopleIncreaseMonthMapper.getPeopleIncrease3();
        peopleIncrease3.setName("3月");
        return peopleIncrease3;
    }

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease4() {
        PeopleIncrease peopleIncrease4 = peopleIncreaseMonthMapper.getPeopleIncrease4();
        peopleIncrease4.setName("4月");
        return peopleIncrease4;
    }

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease5() {
        PeopleIncrease peopleIncrease5 = peopleIncreaseMonthMapper.getPeopleIncrease5();
        peopleIncrease5.setName("5月");
        return peopleIncrease5;
    }

    @Transactional
    @Override
    public PeopleIncrease getPeopleIncrease6() {
        PeopleIncrease peopleIncrease6 = peopleIncreaseMonthMapper.getPeopleIncrease6();
        peopleIncrease6.setName("6月");
        return peopleIncrease6;
    }

}
