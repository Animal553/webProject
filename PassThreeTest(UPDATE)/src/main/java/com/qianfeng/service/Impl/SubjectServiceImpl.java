package com.qianfeng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.Page.Page;
import com.qianfeng.Page.Result;
import com.qianfeng.entity.Subject;
import com.qianfeng.mapper.SubjectMapper;
import com.qianfeng.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Transactional
    @Override
    public Result addSubject(Subject subject) {
        subjectMapper.addSubject(subject);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }

    @Transactional
    @Override
    public Result deleteSubject(Integer subjectId) {
        subjectMapper.deleteSubject(subjectId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }

    @Transactional
    @Override
    public Result updateSubject(Subject subject) {
        subjectMapper.updateSubject(subject);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");

        return result;
    }

    @Transactional
    @Override
    public Result getSubjectList(Integer pageNo, Integer pageSize) {

        if (pageNo==null){
            pageNo=1;
        }
        if (pageSize==null){
            pageSize=4;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<Subject> subjectList = subjectMapper.getSubjectList();
        PageInfo<Subject> info = new PageInfo<>(subjectList);

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
    public Result getSubject(Integer subjectId) {

        Subject subject = subjectMapper.getSubject(subjectId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(subject);

        return result;
    }
}
