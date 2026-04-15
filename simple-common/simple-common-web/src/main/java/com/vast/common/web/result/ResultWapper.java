package com.vast.common.web.result;

import com.vast.common.web.enums.ResultCode;

/**
 * Copyright (C), 2020-2026, c-vast工作室
 *
 * @FileName: ResultWapper
 * @Author: hechenghao1998@foxmail.com
 * @Date: 2026/2/6 13:26
 * @Description:
 * @since 1.0.0
 */
public class ResultWapper {
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS);
    }
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static Result<?> failure(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static Result<?> failure(Integer code, String message) {
        return new Result<>(code, message);
    }

    public static <T> Result<T> failure(ResultCode resultCode, T data) {
        return new Result<>(resultCode, data);
    }
    public static Result<?> failure(ResultCode resultCode, String message) {
        return new Result<>(resultCode.getCode(),message);
    }
}
