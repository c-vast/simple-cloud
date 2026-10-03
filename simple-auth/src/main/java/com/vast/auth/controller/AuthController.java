package com.vast.auth.controller;


import com.vast.auth.dto.LoginResultDTO;
import com.vast.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResultDTO login(@RequestParam String username, @RequestParam String password) {
        return authService.login(username, password);
    }

    @PostMapping("/refreshToken")
    public LoginResultDTO refreshToken(@RequestParam String token, @RequestParam String refreshToken){
        return authService.refreshToken(token, refreshToken);
    }
}
