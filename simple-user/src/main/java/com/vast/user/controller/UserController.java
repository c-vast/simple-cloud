package com.vast.user.controller;

import com.vast.common.annotation.valid.InsertValid;
import com.vast.common.web.result.Result;
import com.vast.common.web.result.ResultWapper;
import com.vast.user.dto.UserDTO;
import com.vast.user.service.UserService;
import com.vast.user.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result<?> register(@RequestBody @Validated(InsertValid.class) UserVO userVO) {
        userService.register(userVO);
        return ResultWapper.success("注册成功");
    }

    @Operation(summary = "根据用户名查询用户")
    @GetMapping("/getUser")
    public UserDTO getUser(String username) {
        return userService.getUserByUsername(username);
    }
}
