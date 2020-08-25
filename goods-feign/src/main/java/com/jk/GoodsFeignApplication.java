package com.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//添加熔断降级注解
@EnableCircuitBreaker
public class GoodsFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsFeignApplication.class, args);
    }

}
