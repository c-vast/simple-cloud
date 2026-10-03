package com.vast.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Copyright (C), 2020-2026, c-vast工作室
 *
 * @FileName: UserApplcation
 * @Author: hechenghao1998@foxmail.com
 * @Date: 2026/9/26 16:42
 * @Description:
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan(value = "com.vast")
@MapperScan("com.vast.user.mapper")
@EnableTransactionManagement
public class UserApplcation {
    public static void main(String[] args) {
        SpringApplication.run(UserApplcation.class, args);
    }
}
