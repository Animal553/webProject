package com.qianfeng.mapper;

import com.qianfeng.entity.SubjectType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectTypeMapper {
    void addSubjectType(SubjectType subjectType);
    void deleteSubjectType(Integer subjectTypeId);
    void updateSubjectType(SubjectType subjectType);
    List<SubjectType> getSubjectTypeList();
    SubjectType getSubjectType(Integer subjectTypeId);
}
