package com.vast.iot.gateway;

import com.vast.iot.gateway.annotation.EnableNettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Copyright (C), 2020-2026, c-vast工作室
 *
 * @FileName: IotGatewayApplication
 * @Author: hechenghao1998@foxmail.com
 * @Date: 2026/3/14 17:00
 * @Description:
 * @since 1.0.0
 */
@EnableNettyServer
@SpringBootApplication
public class IotGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(IotGatewayApplication.class,args);
    }
}
