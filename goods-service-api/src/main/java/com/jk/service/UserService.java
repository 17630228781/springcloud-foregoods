package com.jk.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "tb-provider-two")
public interface UserService {


}
