package com.qianfeng.service;

import com.qianfeng.entity.Category;

import java.util.List;
public interface CategoryService {
    Category selectCategory(Integer categoryId);

    List<Category> selectCategoryList();
}
