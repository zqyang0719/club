package com.football.fans.place.common.service.impl;

import com.football.fans.place.common.service.IUserService;
import com.football.fans.place.dao.UserDao;
import com.football.fans.place.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    public UserDao userDao;

    @Override
    public UserEntity queryUserByMobile(String mobile) {
        return userDao.queryUserByMobile(mobile);
    }
}
