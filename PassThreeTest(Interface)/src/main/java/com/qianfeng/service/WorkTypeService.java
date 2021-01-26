package com.qianfeng.service;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.WorkType;

import java.util.List;

public interface WorkTypeService {
    Result getWorkTypeList();
    Result getWorkType(Integer workTypeId);
}
