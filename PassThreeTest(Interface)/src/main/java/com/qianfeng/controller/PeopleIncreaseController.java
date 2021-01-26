package com.qianfeng.controller;


import com.qianfeng.Page.Result;
import com.qianfeng.entity.PeopleIncrease;
import com.qianfeng.service.PeopleIncreaseService;
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
public class PeopleIncreaseController {

    @Autowired
    private PeopleIncreaseService peopleIncreaseService;

    @RequiresRoles(value = {"teacher","y架构师"},logical = Logical.OR)
    @RequestMapping("/getHomeWeek")
    @ResponseBody
    public Result getHomeWeek(){

        List<PeopleIncrease> peopleIncreaseList =new ArrayList<>();

        PeopleIncrease peopleIncrease1 = peopleIncreaseService.getPeopleIncrease1();
        PeopleIncrease peopleIncrease2 = peopleIncreaseService.getPeopleIncrease2();
        PeopleIncrease peopleIncrease3 = peopleIncreaseService.getPeopleIncrease3();
        PeopleIncrease peopleIncrease4 = peopleIncreaseService.getPeopleIncrease4();
        PeopleIncrease peopleIncrease5 = peopleIncreaseService.getPeopleIncrease5();
        PeopleIncrease peopleIncrease6 = peopleIncreaseService.getPeopleIncrease6();
        PeopleIncrease peopleIncrease7 = peopleIncreaseService.getPeopleIncrease7();
        peopleIncreaseList.add(peopleIncrease1);
        peopleIncreaseList.add(peopleIncrease2);
        peopleIncreaseList.add(peopleIncrease3);
        peopleIncreaseList.add(peopleIncrease4);
        peopleIncreaseList.add(peopleIncrease5);
        peopleIncreaseList.add(peopleIncrease6);
        peopleIncreaseList.add(peopleIncrease7);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(peopleIncreaseList);

        return result;
    }

}
