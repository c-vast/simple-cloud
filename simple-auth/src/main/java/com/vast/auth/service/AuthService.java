package com.vast.auth.service;

import com.vast.auth.dto.LoginResultDTO;

public interface AuthService {
    LoginResultDTO login(String username, String password);
    LoginResultDTO refreshToken(String token, String refreshToken);
}
