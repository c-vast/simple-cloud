package com.vast.common.web.interceptor;

import com.vast.common.web.context.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C), 2020-2026, c-vast工作室
 *
 * @FileName: UserAuthInterceptor
 * @Author: hechenghao1998@foxmail.com
 * @Date: 2026/9/26 16:12
 * @Description:
 * @since 1.0.0
 */
@Slf4j
@Component
public class UserAuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("X-User-Id");
        String username = request.getHeader("X-Username");
        if (!StringUtils.isEmpty(userId)) {
            BaseContextHandler.set("currentUserId",userId);
        }
        if (!StringUtils.isEmpty(username)) {
            BaseContextHandler.set("currentUser",username);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
