package com.qianfeng.controller;


import com.qianfeng.entity.Category;
import com.qianfeng.entity.Goods;
import com.qianfeng.enums.WeekTestEnums;
import com.qianfeng.myException.MyselfException;
import com.qianfeng.page.Page;
import com.qianfeng.page.Page$;
import com.qianfeng.service.CategoryService;
import com.qianfeng.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CategoryService categoryService;

    @CrossOrigin
    @RequestMapping(value = "/getGoodsList")
    @ResponseBody
    public Page$ getGoodsList(Integer pageNo, Integer pageSize){

        Page$ page$ = new Page$();
        if (pageNo==null){
            pageNo=1;
        }
        if (pageSize==null){
            pageSize=5;
        }
        Page page =null;
        try{
             page = goodsService.getGoodsList(pageNo, pageSize);
        }catch (Exception e){
            throw new MyselfException(WeekTestEnums.SELECT_ERROR);
        }

        page$.setCode(200);
        page$.setMsg("查询成功");
        page$.setData(page);

        return page$;
    }

    @CrossOrigin
    @RequestMapping(value = "/addGoods")
    @ResponseBody
    public Page$ addGoods(Goods goods){

        Page$ page$ =null;
        try{
            page$ = goodsService.addGood(goods);
        }catch (Exception e){
            throw new MyselfException(WeekTestEnums.ADD_ERROR);
        }
        return page$;
    }
    @CrossOrigin
    @RequestMapping(value = "/deleteGoods")
    @ResponseBody
    public Page$ deleteGoods(Integer goodsId){

        Page$ page$ =null;
        try{
            page$ = goodsService.deleteGood(goodsId);
        }catch (Exception e){
            throw new MyselfException(WeekTestEnums.DELETE_ERROR);
        }
        return page$;
    }
    @CrossOrigin
    @RequestMapping(value = "/updateGoods")
    @ResponseBody
    public Page$ updateGoods(Goods goods){
        System.out.println(goods.getCreateTime());
        System.out.println("商品ID"+goods.getGoodsId());
        System.out.println("商品类型"+goods.getCategoryId());
        Page$ page$ =null;
        try{
            page$ = goodsService.updateGood(goods);
        }catch (Exception e){
            throw new MyselfException(WeekTestEnums.UPDATE_ERROR);
        }
        return page$;
    }

    @CrossOrigin
    @RequestMapping(value = "/getCategoryList")
    @ResponseBody
    public Page$ getCategoryList(){

        List<Category> categoryList =null;
        try{
            categoryList = categoryService.getCategoryList();
        }catch (Exception e){
            throw new MyselfException(WeekTestEnums.SELECT_ERROR);
        }
        Page$ page$ = new Page$();
        page$.setCode(200);
        page$.setMsg("查找类型成功");
        page$.setData(categoryList);
        return page$;
    }
}
