package com.vast.iot.gateway.annotation;

import com.vast.iot.gateway.boot.ServerBoot;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Copyright (C), 2020-2026, c-vast工作室
 *
 * @FileName: EnableNettyServer
 * @Author: hechenghao1998@foxmail.com
 * @Date: 2026/4/15 17:22
 * @Description:
 * @since 1.0.0
 */
@Import(ServerBoot.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableNettyServer {
}
