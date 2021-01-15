package com.qianfeng.service;

import com.qianfeng.entity.Level;

import java.util.List;

public interface LevelService {
    List<Level> getLevelList();
    Level getLevel(Integer levelId);
}
