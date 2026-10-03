package com.vast.auth.dto;

import lombok.Data;

@Data
public class LoginResultDTO {
    private String token;
    private String refreshToken;
    private Integer expireTime;
}
