package com.vast.user.controller;

import com.vast.common.annotation.valid.InsertValid;
import com.vast.common.web.result.Result;
import com.vast.common.web.result.ResultWapper;
import com.vast.user.dto.UserDTO;
import com.vast.user.service.UserService;
import com.vast.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<?> register(@RequestBody @Validated(InsertValid.class) UserVO userVO) {
        userService.register(userVO);
        return ResultWapper.success("注册成功");
    }

    @GetMapping("/getUser")
    public UserDTO getUser(String username) {
        return userService.getUserByUsername(username);
    }
}
