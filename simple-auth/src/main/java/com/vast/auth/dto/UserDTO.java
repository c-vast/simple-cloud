package com.vast.auth.dto;

import com.vast.common.base.dto.BaseDTO;
import lombok.Data;

@Data
public class UserDTO extends BaseDTO<Long> {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String mobile;
}
