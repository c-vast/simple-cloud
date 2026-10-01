package com.vast.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vast.common.base.entity.BaseLogicDO;
import lombok.Data;

@Data
@TableName("t_user")
public class UserDO extends BaseLogicDO<Long,UserDO> {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String mobile;
    private Integer enable;
}
