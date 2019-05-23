package com.dd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//允许拉取注册中心服务列表
public class ItemApp {
    public static void main(String[] args) {
        SpringApplication.run(ItemApp.class);
    }
}
