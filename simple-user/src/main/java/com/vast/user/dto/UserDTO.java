package com.vast.user.dto;

import com.vast.common.base.dto.BaseDTO;
import lombok.Data;

@Data
public class UserDTO extends BaseDTO<Long> {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String mobile;
}
