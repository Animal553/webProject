package com.qianfeng.service;

import com.qianfeng.entity.Goods;
import com.qianfeng.page.Page;
import com.qianfeng.page.Page$;


public interface GoodsService {
    //查全部
    Page getGoodsList(Integer pageNo, Integer pageSize);

    //查单个
    Page$ getGoods(Integer goodsId);

    //增加
    Page$ addGood(Goods goods);

    //删除
    Page$ deleteGood(Integer goodsId);

    //修改
    Page$ updateGood(Goods goods);
}
