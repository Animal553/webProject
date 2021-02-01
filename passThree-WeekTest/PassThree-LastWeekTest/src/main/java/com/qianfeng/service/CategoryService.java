package com.qianfeng.service;

import com.qianfeng.entity.Category;
import com.qianfeng.page.Page$;

import java.util.List;


public interface CategoryService {

    //查全部
    List<Category> getCategoryList();

    //查单个
    Category getCategory(Integer categoryId);

    //增加
    Page$ addCategory(Category category);

    //删除
    Page$ deleteCategory(Integer categoryId);

    //修改
    Page$ updateCategory(Category category);
}
