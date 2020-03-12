package com.qf.ssoservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.qf.mapper")
public class ShopSsoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopSsoServiceApplication.class, args);
    }

}
