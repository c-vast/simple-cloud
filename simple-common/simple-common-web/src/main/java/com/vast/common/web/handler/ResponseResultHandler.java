package com.vast.common.web.handler;

import com.vast.common.web.result.Result;
import com.vast.common.web.result.ResultWapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Copyright (C), 2020-2021, c-vast
 *
 * @version 1.0.0
 * @className: ResponseResultHander
 * @author: hechenghao1998@foxmail.com
 * @createDate: 2021/7/18 0:29
 * @description: 响应结果处理
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Class<?> returnTypeClass = methodParameter.getParameterType();
        return !Result.class.isAssignableFrom(returnTypeClass)
                && !ResponseEntity.class.isAssignableFrom(returnTypeClass);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof Result || o instanceof String) {
            return o;
        }
        return ResultWapper.success(o);
    }
}
