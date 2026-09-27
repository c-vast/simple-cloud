package com.vast.auth.service.impl;

import com.vast.auth.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(String username, String password) {
        return "";
    }

    @Override
    public String refreshToken(String token) {
        return "";
    }
}
