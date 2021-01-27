package com.qianfeng.mapper;

import com.qianfeng.entity.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMapper {
    void addSubject(Subject subject);
    void deleteSubject(Integer subjectId);
    void updateSubject(Subject subject);
    List<Subject> getSubjectList();
    Subject getSubject(Integer subjectId);
}
