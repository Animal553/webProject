package com.qianfeng.mapper;

import com.qianfeng.entity.Education;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationMapper {
    List<Education> getEducationList(Integer pageNo, Integer educationId);
    Education getEducation(Integer educationId);
}
