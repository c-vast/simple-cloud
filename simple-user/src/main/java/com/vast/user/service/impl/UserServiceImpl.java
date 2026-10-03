package com.vast.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vast.common.web.exception.BusinessException;
import com.vast.user.dto.UserDTO;
import com.vast.user.entity.UserDO;
import com.vast.user.mapper.UserMapper;
import com.vast.user.service.UserService;
import com.vast.user.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void register(UserVO userVO) {
        if (userVO == null) throw new IllegalArgumentException("userVO is null");

        long count = userMapper.selectCount(new QueryWrapper<UserDO>().eq("username", userVO.getUsername()));
        if (count > 0) throw new BusinessException("用户名已存在");

        long mobileCount = userMapper.selectCount(new QueryWrapper<UserDO>().eq("mobile", userVO.getMobile()));
        if (mobileCount > 0) throw new BusinessException("手机号已存在");

        long emailCount = userMapper.selectCount(new QueryWrapper<UserDO>().eq("email", userVO.getEmail()));
        if (emailCount > 0) throw new BusinessException("邮箱已存在");


        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userVO, userDO);
        userDO.setEnable(1);
        int insert = userMapper.insert(userDO);
        if (insert != 1) throw new BusinessException("插入失败");
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("username is null");
        UserDO userDO = userMapper.selectOne(new QueryWrapper<UserDO>().eq("username", username));
        if (userDO == null) {
           throw new BusinessException("未找到用户");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }
}
