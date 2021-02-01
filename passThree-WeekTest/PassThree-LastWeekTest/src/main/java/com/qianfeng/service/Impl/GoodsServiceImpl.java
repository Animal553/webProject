package com.qianfeng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.entity.Goods;
import com.qianfeng.mapper.GoodsMapper;
import com.qianfeng.page.Page;
import com.qianfeng.page.Page$;
import com.qianfeng.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Transactional
    @Override
    public Page getGoodsList(Integer pageNo, Integer pageSize) {
        List<Goods> goodsList = goodsMapper.getGoodsList();
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<Goods> info = new PageInfo<>(goodsList);
        Page page = new Page();
        page.setPageNo(info.getPageNum());
        page.setPageSize(info.getPageSize());
        page.setHasNext(info.isHasNextPage());
        page.setHasPre(info.isHasPreviousPage());
        page.setCount(info.getTotal());
        page.setPageCount(info.getPages());
        page.setData(info.getList());
        return page;
    }


    @Transactional
    @Override
    public Page$ getGoods(Integer goodsId) {
        return null;
    }

    @Transactional
    @Override
    public Page$ addGood(Goods goods) {
        goodsMapper.addGood(goods);
        Page$ page$ = new Page$();
        page$.setCode(200);
        page$.setMsg("添加成功");
        page$.setData(null);

        return page$;
    }

    @Transactional
    @Override
    public Page$ deleteGood(Integer goodsId) {
        goodsMapper.deleteGood(goodsId);
        Page$ page$ = new Page$();
        page$.setCode(200);
        page$.setMsg("删除成功");
        page$.setData(null);
        return page$;
    }

    @Transactional
    @Override
    public Page$ updateGood(Goods goods) {
        goodsMapper.updateGood(goods);
        Page$ page$ = new Page$();
        page$.setCode(200);
        page$.setMsg("修改成功");
        page$.setData(null);
        return page$;
    }
}
