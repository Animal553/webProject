package com.qianfeng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.Page.Page;
import com.qianfeng.Page.Result;
import com.qianfeng.entity.Class;
import com.qianfeng.mapper.ClassMapper;
import com.qianfeng.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Transactional
    @Override
    public Result addClass(Class clazz) {
        classMapper.addClass(clazz);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }

    @Transactional
    @Override
    public Result deleteClass(Integer classId) {
        classMapper.deleteClass(classId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }

    @Transactional
    @Override
    public Result updateClass(Class clazz) {
        classMapper.updateClass(clazz);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }

    @Transactional
    @Override
    public Result getClassList(Integer pageNo, Integer pageSize) {

        if (pageNo==null){
            pageNo=1;
        }
        if (pageSize==null){
            pageSize=4;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<Class> classList = classMapper.getClassList();
        PageInfo<Class> info = new PageInfo<>(classList);

        Page page = new Page();
        page.setHasNext(info.isHasNextPage());
        page.setHasPre(info.isHasPreviousPage());
        page.setPageNo(info.getPageNum());
        page.setPageSize(info.getPageSize());
        page.setPageCount(info.getPages());
        page.setPage(info.getList());

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(page);

        return result;
    }

    @Transactional
    @Override
    public Result getClass(Integer classId) {

        Class classMapperClass = classMapper.getClass(classId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(classMapperClass);

        return result;
    }
}
