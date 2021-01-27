package com.qianfeng.service;

import com.qianfeng.Page.Result;

public interface EducationService {
    Result getEducationList(Integer pageNo, Integer educationId);
    Result getEducation(Integer educationId);
}
