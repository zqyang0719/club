package com.football.fans.place.common.service;

import com.football.fans.place.entity.UserEntity;

public interface IUserService {
    public UserEntity queryUserByMobile(String mobile);
}
