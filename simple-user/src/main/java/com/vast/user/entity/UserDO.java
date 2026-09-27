package com.vast.user.entity;

import lombok.Data;

@Data
public class UserDO extends BaseDO<Long>{
    private String username;
    private String password;
    private String email;
    private String phone;
}
