package com.vast.auth.dto;

import lombok.Data;

@Data
public class LoginResultDTO {
    private String accessToken;
    private String refreshToken;
}
