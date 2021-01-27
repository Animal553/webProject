package com.qianfeng.service;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.SubjectType;


public interface SubjectTypeService {
    Result addSubjectType(SubjectType subjectType);
    Result deleteSubjectType(Integer subjectTypeId);
    Result updateSubjectType(SubjectType subjectType);
    Result getSubjectTypeList(Integer pageNo,Integer pageSize);
    Result getSubjectType(Integer subjectTypeId);
}
