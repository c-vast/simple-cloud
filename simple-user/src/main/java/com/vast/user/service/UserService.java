package com.vast.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vast.user.dto.UserDTO;
import com.vast.user.entity.UserDO;
import com.vast.user.vo.UserVO;

import java.util.List;

public interface UserService {

    void register(UserVO userVO);
    UserDTO getUserByUsername(String username);
    Page<UserDO> getPageUsers(Integer page, Integer size);
}
