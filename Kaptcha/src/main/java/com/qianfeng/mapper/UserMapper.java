package com.qianfeng.mapper;

import com.qianfeng.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User getUser(User user);
}
