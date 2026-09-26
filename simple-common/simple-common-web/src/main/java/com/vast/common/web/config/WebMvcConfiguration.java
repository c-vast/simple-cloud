package com.vast.common.web.config;

import com.vast.common.web.interceptor.LogInterceptor;
import com.vast.common.web.interceptor.UserAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright (C), 2020-2026, c-vast工作室
 *
 * @FileName: WebMvcConfiguration
 * @Author: hechenghao1998@foxmail.com
 * @Date: 2026/3/13 17:19
 * @Description:
 * @since 1.0.0
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private LogInterceptor logInterceptor;

    @Autowired
    private UserAuthInterceptor userAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
        registry.addInterceptor(userAuthInterceptor);
    }
}
