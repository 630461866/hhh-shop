package com.qf.shopemailservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.qf.mapper")
public class ShopEmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopEmailServiceApplication.class, args);
    }

}
