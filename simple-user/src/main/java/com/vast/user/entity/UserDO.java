package com.vast.user.entity;

import com.vast.common.base.entity.BaseDO;
import lombok.Data;

@Data
public class UserDO extends BaseDO<Long> {
    private String username;
    private String password;
    private String email;
    private String phone;
}
