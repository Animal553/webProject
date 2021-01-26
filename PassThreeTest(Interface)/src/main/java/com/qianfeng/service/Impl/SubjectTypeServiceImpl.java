package com.qianfeng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.Page.Page;
import com.qianfeng.Page.Result;
import com.qianfeng.entity.SubjectType;
import com.qianfeng.mapper.SubjectTypeMapper;
import com.qianfeng.service.SubjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectTypeServiceImpl implements SubjectTypeService {

    @Autowired
    private SubjectTypeMapper subjectTypeMapper;

    @Transactional
    @Override
    public Result addSubjectType(SubjectType subjectType) {
        subjectTypeMapper.addSubjectType(subjectType);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result deleteSubjectType(Integer subjectTypeId) {
        subjectTypeMapper.deleteSubjectType(subjectTypeId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result updateSubjectType(SubjectType subjectType) {
        subjectTypeMapper.updateSubjectType(subjectType);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result getSubjectTypeList(Integer pageNo, Integer pageSize) {

        if (pageNo==null){
            pageNo=1;
        }
        if (pageSize==null){
            pageSize=4;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<SubjectType> subjectTypeList = subjectTypeMapper.getSubjectTypeList();
        PageInfo<SubjectType> info = new PageInfo<>(subjectTypeList);

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
    public Result getSubjectType(Integer subjectTypeId) {
        SubjectType subjectType = subjectTypeMapper.getSubjectType(subjectTypeId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(subjectType);

        return result;
    }
}
