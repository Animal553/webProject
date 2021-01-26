package com.qianfeng.mapper;

import com.qianfeng.entity.WorkType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTypeMapper {
    List<WorkType> getWorkTypeList();
    WorkType getWorkType(Integer workTypeId);
}
