package com.vast.auth.controller;


import com.vast.auth.dto.LoginResultDTO;
import com.vast.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "鉴权管理")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public LoginResultDTO login(@RequestParam String username, @RequestParam String password) {
        return authService.login(username, password);
    }

    @Operation(summary = "刷新token")
    @PostMapping("/refreshToken")
    public LoginResultDTO refreshToken(@RequestParam String accessToken, @RequestParam String refreshToken){
        return authService.refreshToken(accessToken, refreshToken);
    }
}
