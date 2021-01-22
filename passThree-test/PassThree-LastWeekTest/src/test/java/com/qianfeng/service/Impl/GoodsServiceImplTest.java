package com.qianfeng.service.Impl;

import com.qianfeng.entity.Category;
import com.qianfeng.entity.Goods;
import com.qianfeng.service.CategoryService;
import com.qianfeng.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class GoodsServiceImplTest {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CategoryService categoryService;
    @Test
    public void getCategoryList() {
        List<Category> categoryList = categoryService.getCategoryList();
        for (Category category : categoryList) {
            System.out.println(category);
        }
    }

    @Test
    public void getGoods() {
    }

    @Test
    public void addGood() {
        Goods goods = new Goods();
        goods.setGoodsName("lilei1");
        goods.setPrice(1000);
        goods.setCategoryId(1001);
        goods.setCreateTime(new Date());
        goodsService.addGood(goods);
    }

    @Test
    public void deleteGood() {
    }

    @Test
    public void updateGood() {
    }
}