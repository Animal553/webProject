package com.qianfeng.service;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Subject;


public interface SubjectService {

    Result addSubject(Subject subject);
    Result deleteSubject(Integer subjectId);
    Result updateSubject(Subject subject);
    Result getSubjectList(Integer pageNo, Integer pageSize);
    Result getSubject(Integer subjectId);
}
