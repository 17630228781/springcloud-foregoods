package com.jk.controller;

import com.jk.service.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utils.RedisUtil;


import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserServiceFeign userService;





}
