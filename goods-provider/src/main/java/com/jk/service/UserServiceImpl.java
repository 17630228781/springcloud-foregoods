package com.jk.service;

import com.jk.mapper.UserMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

}
