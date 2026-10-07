package com.vast.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vast.common.redis.component.RedisOperator;
import com.vast.common.web.exception.BusinessException;
import com.vast.user.dto.UserDTO;
import com.vast.user.entity.UserDO;
import com.vast.user.mapper.UserMapper;
import com.vast.user.service.UserService;
import com.vast.user.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisOperator redisOperator;

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

        redisOperator.hashSet("user:username",userDO.getUsername(), userDO);
        redisOperator.hashSet("user:id", String.valueOf(userDO.getId()), userDO);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("username is null");
        UserDO userDO;
        if (redisOperator.hashExists("user:username", username)) {
            userDO = (UserDO) redisOperator.hashGet("user:username", username);
        }else {
            userDO = userMapper.selectOne(new QueryWrapper<UserDO>().eq("username", username));

            redisOperator.hashSet("user:username",userDO.getUsername(), userDO);
            redisOperator.hashSet("user:id", String.valueOf(userDO.getId()), userDO);
        }
        if (userDO == null) {
           throw new BusinessException("未找到用户");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public UserDO getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @CachePut(key = "#user.id")
    public UserDO updateUser(UserDO user) {
        userMapper.updateById(user);
        return user;
    }

    @CacheEvict(key = "#id")
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }
}
