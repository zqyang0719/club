package com.football.fans.place.dao;

import com.football.fans.place.entity.UserEntity;

public interface UserDao {

    UserEntity queryUserByMobile(String mobile);
}
