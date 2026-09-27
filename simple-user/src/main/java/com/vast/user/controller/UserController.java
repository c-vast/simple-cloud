package com.vast.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    public String register(String username, String password) {
        return "register";
    }

    @GetMapping("/getUser")
    public String getUser(String username, String password) {
        return "getUser";
    }
}
