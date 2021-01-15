package com.qianfeng.service;

import com.qianfeng.entity.Source;

import java.util.List;

public interface SourceService {
    List<Source> selectSourceList();
    Source selectSource(Integer sourceId);
}
