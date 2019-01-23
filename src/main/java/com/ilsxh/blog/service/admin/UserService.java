package com.ilsxh.blog.service.admin;

import com.ilsxh.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Integer login(String username, String password) {
        return userMapper.selectLoginUser(username, password);
    }
}
