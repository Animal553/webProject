package com.qianfeng.service;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Role;

import java.util.List;

public interface RoleService {
    Result add_role(Role role);
    Result delete_role(Integer roleId);
    Result update_role(Role role);
    Result get_roleList(Integer pageNo,Integer pageSize);
    Result get_role(Integer roleId);
}
