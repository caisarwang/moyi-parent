package com.dd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 授权中心-登录认证
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class);
    }
}
