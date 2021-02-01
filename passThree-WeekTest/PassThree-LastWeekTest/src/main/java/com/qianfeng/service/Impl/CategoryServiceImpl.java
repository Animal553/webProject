package com.qianfeng.service.Impl;

import com.qianfeng.entity.Category;
import com.qianfeng.mapper.CategoryMapper;
import com.qianfeng.page.Page$;
import com.qianfeng.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = categoryMapper.getCategoryList();
        return categoryList;
    }

    @Transactional
    @Override
    public Category getCategory(Integer categoryId) {
        Category category = categoryMapper.getCategory(categoryId);
        return category;
    }

    @Transactional
    @Override
    public Page$ addCategory(Category category) {
        return null;
    }

    @Transactional
    @Override
    public Page$ deleteCategory(Integer categoryId) {
        return null;
    }

    @Transactional
    @Override
    public Page$ updateCategory(Category category) {
        return null;
    }
}
