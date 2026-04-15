package com.vast.iot.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C), 2020-2026, c-vast工作室
 *
 * @FileName: NettyProperties
 * @Author: hechenghao1998@foxmail.com
 * @Date: 2026/3/21 16:29
 * @Description:
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {
    // boss线程数量 默认为cpu线程数*4

    private Integer boss;

    // worker线程数量 默认为cpu线程数*2

    private Integer worker;

    // 连接超时时间 默认为30s

    private Integer timeout = 30000;

    // 服务器主端口 默认9000

    private Integer port = 9000;

    // 服务器地址 默认为本地

    private String host = "127.0.0.1";
}
