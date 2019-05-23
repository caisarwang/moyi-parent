package com.dd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer//开启eureka-server
@SpringBootApplication//声明为启动类
public class EurekaApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApp.class);
    }
}
