package com.qianfeng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.Page.Page;
import com.qianfeng.Page.Result;
import com.qianfeng.entity.Attendance;
import com.qianfeng.entity.Education;
import com.qianfeng.mapper.EducationMapper;
import com.qianfeng.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationMapper educationMapper;

    @Transactional
    @Override
    public Result getEducationList(Integer pageNo, Integer pageSize) {

        if (pageNo==null){
            pageNo=1;
        }
        if (pageSize==null){
            pageSize=4;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<Education> educationList = educationMapper.getEducationList(pageNo, pageSize);
        PageInfo<Education> info = new PageInfo<>(educationList);

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
    public Result getEducation(Integer educationId) {

        Education education = educationMapper.getEducation(educationId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(education);

        return result;
    }
}
