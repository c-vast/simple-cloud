package com.vast.common.base.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDO<ID> {
    private ID id;
    private Date createTime;
    private Date updateTime;
}
