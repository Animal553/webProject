package com.qianfeng.mapper;

import com.qianfeng.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    void add_role(Role role);
    void delete_role(Integer roleId);
    void update_role(Role role);
    List<Role> get_roleList();
    Role get_role(Integer roleId);
}
