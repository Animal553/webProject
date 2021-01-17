package com.qianfeng.service.Impl;

import com.qianfeng.entity.User;
import com.qianfeng.mapper.UserMapper;
import com.qianfeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUser(User user) {
        User mapperUser = userMapper.getUser(user);
        return mapperUser;
    }
}
