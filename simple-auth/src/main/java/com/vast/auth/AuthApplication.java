package com.vast.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Copyright (C), 2020-2026, c-vast工作室
 *
 * @FileName: AuthApplication
 * @Author: hechenghao1998@foxmail.com
 * @Date: 2026/4/16 14:53
 * @Description:
 * @since 1.0.0
 */
@ComponentScan(value = "com.vast")
@EnableFeignClients(basePackages = "com.vast.auth.feign")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}
