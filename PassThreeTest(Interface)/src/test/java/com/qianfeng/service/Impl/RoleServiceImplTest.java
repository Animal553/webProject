package com.qianfeng.service.Impl;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Role;
import com.qianfeng.mapper.RoleMapper;
import com.qianfeng.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;


    @Test
    public void add_role() {
        Role role = new Role();
        role.setRoleName("测试添加角色1");
        role.setStatus("有效");
        roleService.add_role(role);
    }

    @Test
    public void delete_role() {
        roleService.delete_role(10034);
    }

    @Test
    public void update_role() {
        Role role = new Role();
        role.setRoleId(10035);
        role.setRoleName("测试修改");
        role.setStatus("修改状态");
        roleService.update_role(role);
    }

    @Test
    public void get_roleList() {

//        List<Role> roleList = roleMapper.get_roleList();
//        for (Role role : roleList) {
//            System.out.println(role);
//        }

        Result roleList = roleService.get_roleList(1, 4);
        System.out.println(roleList);

    }

    @Test
    public void get_role() {
        Result role = roleService.get_role(10035);
        System.out.println(role);

    }
}