package com.qianfeng.service;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Class;


public interface ClassService {
    Result addClass(Class clazz);
    Result deleteClass(Integer classId);
    Result updateClass(Class clazz);
    Result getClassList(Integer pageNo,Integer pageSize);
    Result getClass(Integer classId);
}
