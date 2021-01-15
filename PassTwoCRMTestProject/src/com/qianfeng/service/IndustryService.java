package com.qianfeng.service;

import com.qianfeng.entity.Industry;
import java.util.List;

public interface IndustryService {
    List<Industry> selectIndustryList();
    Industry selectIndustry(Integer industryId);
}
