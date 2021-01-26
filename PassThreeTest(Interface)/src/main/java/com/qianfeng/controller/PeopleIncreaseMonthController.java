package com.qianfeng.controller;


import com.qianfeng.Page.Result;
import com.qianfeng.entity.PeopleIncrease;
import com.qianfeng.entity.PeopleIncreaseMonth;
import com.qianfeng.service.PeopleIncreaseMonthService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequiresRoles(value = {"teacher","manager"},logical = Logical.OR)
@CrossOrigin
@Controller
public class PeopleIncreaseMonthController {
    @Autowired
    private PeopleIncreaseMonthService peopleIncreaseMonthService;

    @RequiresRoles(value = {"teacher","manager"},logical = Logical.OR)
    @RequestMapping("/getHomeMonth")
    @ResponseBody
    public Result getHomeMonth(){

        PeopleIncreaseMonth peopleIncreaseMonth = new PeopleIncreaseMonth();

        List<Integer> integerList =new ArrayList<>();
        List<String> stringList =new ArrayList<>();

        PeopleIncrease peopleIncrease1 = peopleIncreaseMonthService.getPeopleIncrease1();
        PeopleIncrease peopleIncrease2 = peopleIncreaseMonthService.getPeopleIncrease2();
        PeopleIncrease peopleIncrease3 = peopleIncreaseMonthService.getPeopleIncrease3();
        PeopleIncrease peopleIncrease4 = peopleIncreaseMonthService.getPeopleIncrease4();
        PeopleIncrease peopleIncrease5 = peopleIncreaseMonthService.getPeopleIncrease5();
        PeopleIncrease peopleIncrease6 = peopleIncreaseMonthService.getPeopleIncrease6();

        System.out.println(peopleIncrease1.getY());

        integerList.add(peopleIncrease1.getY());
        integerList.add(peopleIncrease2.getY());
        integerList.add(peopleIncrease3.getY());
        integerList.add(peopleIncrease4.getY());
        integerList.add(peopleIncrease5.getY());
        integerList.add(peopleIncrease6.getY());

        stringList.add(peopleIncrease1.getName());
        stringList.add(peopleIncrease2.getName());
        stringList.add(peopleIncrease3.getName());
        stringList.add(peopleIncrease4.getName());
        stringList.add(peopleIncrease5.getName());
        stringList.add(peopleIncrease6.getName());

        peopleIncreaseMonth.setData(integerList);
        peopleIncreaseMonth.setCategories(stringList);


        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(peopleIncreaseMonth);

        return result;
    }
}
