package com.jk.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
@Component
public class UserServiceFallback implements UserServiceFeign{



}
