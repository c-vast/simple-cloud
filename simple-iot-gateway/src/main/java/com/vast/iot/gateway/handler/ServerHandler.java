package com.vast.iot.gateway.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Copyright (C), 2020-2026, c-vast工作室
 *
 * @FileName: ServerHandler
 * @Author: hechenghao1998@foxmail.com
 * @Date: 2026/3/21 16:33
 * @Description:
 * @since 1.0.0
 */
public class ServerHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
//        pipeline.addLast(new MessageDecodeHandler());
//        pipeline.addLast(new MessageEncodeHandler());
//        pipeline.addLast(new ServerListenerHandler());
    }
}
