package com.qianfeng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.Page.Page;
import com.qianfeng.Page.Result;
import com.qianfeng.entity.Role;
import com.qianfeng.mapper.RoleMapper;
import com.qianfeng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public Result add_role(Role role) {
        roleMapper.add_role(role);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result delete_role(Integer roleId) {
        roleMapper.delete_role(roleId);
        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result update_role(Role role) {
        roleMapper.update_role(role);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result get_roleList(Integer pageNo,Integer pageSize) {
        if (pageNo==null){
            pageNo=1;
        }
        if (pageSize==null){
            pageSize=4;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<Role> roleList = roleMapper.get_roleList();
        PageInfo<Role> info = new PageInfo<>(roleList);

        Page page = new Page();
        page.setPageNo(info.getPageNum());
        page.setPageSize(info.getPageSize());
        page.setPageCount(info.getPages());
        page.setHasPre(info.isHasPreviousPage());
        page.setHasNext(info.isHasNextPage());
        page.setPage(info.getList());

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(page);

        return result;
    }

    @Transactional
    @Override
    public Result get_role(Integer roleId) {
        Role role = roleMapper.get_role(roleId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(role);

        return result;
    }
}
