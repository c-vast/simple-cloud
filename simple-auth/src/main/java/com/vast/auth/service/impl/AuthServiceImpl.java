package com.vast.auth.service.impl;

import com.vast.auth.dto.LoginResultDTO;
import com.vast.auth.dto.UserDTO;
import com.vast.auth.feign.UserFeignClient;
import com.vast.auth.service.AuthService;
import com.vast.common.web.exception.BusinessException;
import com.vast.common.web.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserFeignClient userFeignClient;
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
        LoginResultDTO loginResultDTO = new LoginResultDTO();
        loginResultDTO.setToken(userDTO.getUsername() + " token");
        loginResultDTO.setRefreshToken(userDTO.getUsername() + " refresh token");
        loginResultDTO.setExpireTime(7200);
        log.info("login success, username: {}, token: {}", username, loginResultDTO.getToken());
        return loginResultDTO;
    }

    @Override
    public LoginResultDTO refreshToken(String token, String refreshToken) {
        LoginResultDTO loginResultDTO = new LoginResultDTO();
        loginResultDTO.setToken(token);
        loginResultDTO.setRefreshToken(refreshToken);
        loginResultDTO.setExpireTime(7200);
        log.info("refresh token success, token: {}, refresh token: {}", token, refreshToken);
        return loginResultDTO;
    }
}
