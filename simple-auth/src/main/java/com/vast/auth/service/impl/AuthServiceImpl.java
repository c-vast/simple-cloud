package com.vast.auth.service.impl;

import com.vast.auth.dto.LoginResultDTO;
import com.vast.auth.dto.UserDTO;
import com.vast.auth.feign.UserFeignClient;
import com.vast.auth.service.AuthService;
import com.vast.common.component.JwtComponent;
import com.vast.common.web.exception.BusinessException;
import com.vast.common.web.result.Result;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private JwtComponent jwtComponent;
    @Override
    public LoginResultDTO login(String username, String password) {
        if (username == null || password == null){
            throw new BusinessException("用户名或密码为空");
        }
        Result<UserDTO> result = userFeignClient.getUser(username);
        if (!result.getSuccess()){
            throw new BusinessException(result.getCode(), result.getMessage());
        }
        UserDTO userDTO = result.getData();
        if (!userDTO.getPassword().equals(password)){
            throw new BusinessException("用户名或密码错误");
        }

        String token = jwtComponent.createAccessToken(userDTO.getId(), userDTO.getUsername());
        String refreshToken = jwtComponent.createRefreshToken(userDTO.getId(), userDTO.getUsername());

        LoginResultDTO loginResultDTO = new LoginResultDTO();
        loginResultDTO.setAccessToken(token);
        loginResultDTO.setRefreshToken(refreshToken);
        log.info("login success, username: {}, token: {}", username, loginResultDTO.getAccessToken());
        return loginResultDTO;
    }

    @Override
    public LoginResultDTO refreshToken(String accessToken, String refreshToken) {

        Claims claims;
        try {
            claims = jwtComponent.parseRefreshToken(refreshToken);
        } catch (Exception e) {
            claims = null;
        }
        if (claims == null) {
            throw new BusinessException("刷新令牌无效");
        }
        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);

        String newAccessToken = jwtComponent.createAccessToken(userId, username);
        String newRefreshToken = jwtComponent.createRefreshToken(userId, username);

        LoginResultDTO loginResultDTO = new LoginResultDTO();
        loginResultDTO.setAccessToken(newAccessToken);
        loginResultDTO.setRefreshToken(newRefreshToken);
        log.info("refresh token success, token: {}, refresh token: {}", newAccessToken, newRefreshToken);
        return loginResultDTO;
    }
}
