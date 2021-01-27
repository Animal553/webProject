package com.qianfeng.service.Impl;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.WorkType;
import com.qianfeng.mapper.WorkTypeMapper;
import com.qianfeng.service.WorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkTypeServiceImpl implements WorkTypeService {

    @Autowired
    private WorkTypeMapper workTypeMapper;

    @Transactional
    @Override
    public Result getWorkTypeList() {
        List<WorkType> workTypeList = workTypeMapper.getWorkTypeList();

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(workTypeList);
        return result;
    }

    @Override
    public Result getWorkType(Integer workTypeId) {
        WorkType workType = workTypeMapper.getWorkType(workTypeId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(workType);
        return result;
    }
}
