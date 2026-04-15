package com.vast.iot.gateway.boot;

import com.vast.iot.gateway.properties.NettyProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Copyright (C), 2020-2026, c-vast工作室
 *
 * @FileName: ServerBoot
 * @Author: hechenghao1998@foxmail.com
 * @Date: 2026/4/15 17:18
 * @Description:
 * @since 1.0.0
 */
@Slf4j
@Component
public class ServerBoot {
    @Autowired
    private ServerBootstrap serverBootstrap;
    @Resource
    private NioEventLoopGroup boosGroup;
    @Resource
    private NioEventLoopGroup workerGroup;
    @Autowired
    private NettyProperties nettyProperties;

    @PostConstruct
    public void start() throws InterruptedException {
        // 绑定端口启动
        serverBootstrap.bind(nettyProperties.getPort()).sync();
        log.info("启动Netty服务器: {}",nettyProperties.getPort());
    }

    /**
     * 关闭线程池
     */
    @PreDestroy
    public void close() throws InterruptedException {
        log.info("关闭Netty服务器");
        boosGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
