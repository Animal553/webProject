package com.qianfeng.mapper;

import com.qianfeng.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    //查全部
    List<Goods> getGoodsList();

    //查单个
    Goods getGoods(Integer goodsId);

    //增加
    void addGood(Goods goods);

    //删除
    void deleteGood(Integer goodIsd);

    //修改
    void updateGood(Goods goods);

}
