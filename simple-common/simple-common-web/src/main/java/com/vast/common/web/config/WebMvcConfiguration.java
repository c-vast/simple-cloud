package com.vast.common.web.config;

import com.vast.common.web.interceptor.LogInterceptor;
import com.vast.common.web.interceptor.UserAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

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

//    @Bean
//    public LocaleResolver localeResolver() {
//        CookieLocaleResolver resolver = new CookieLocaleResolver();
//        resolver.setCookieName("lang");
//        resolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
//        resolver.setCookieMaxAge(365 * 24 * 60 * 60);
//        return resolver;
//    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
        registry.addInterceptor(userAuthInterceptor);
        registry.addInterceptor(localeChangeInterceptor());
    }
}
