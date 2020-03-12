package com.qf.phoneregisterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.qf.mapper")
public class ShopPhoneregisterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopPhoneregisterServiceApplication.class, args);
    }

}
