package com.vast.user.service;

import com.vast.user.dto.UserDTO;
import com.vast.user.vo.UserVO;

public interface UserService {

    void register(UserVO userVO);
    UserDTO getUserByUsername(String username);
}
