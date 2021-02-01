package com.qianfeng.mapper;

import com.qianfeng.entity.Category;
import com.qianfeng.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {

    //查全部
    List<Category> getCategoryList();

    //查单个
    Category getCategory(Integer categoryId);

    //增加
    void addCategory(Category category);

    //删除
    void deleteCategory(Integer categoryId);

    //修改
    void updateCategory(Category category);
}
