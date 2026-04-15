package com.vast.common.web.result;

import com.vast.common.web.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2020-2026, c-vast工作室
 *
 * @FileName: Result
 * @Author: hechenghao1998@foxmail.com
 * @Date: 2026/2/6 13:19
 * @Description: 响应结果
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    public Result(ResultCode resultCode) {
        this.success = code == 0;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode,T data) {
        this.success = code == 0;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(Integer code) {
        this.success = code == 0;
        this.code = code;
        this.message = "success";
    }

    public Result(Integer code, String message) {
        this.success = code == 0;
        this.code = code;
        this.message = message;
    }
    public Result(Integer code, String message,T data) {
        this.success = code == 0;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
