package com.vast.auth.service;

public interface AuthService {
    String login(String username, String password);
    String refreshToken(String token);
}
