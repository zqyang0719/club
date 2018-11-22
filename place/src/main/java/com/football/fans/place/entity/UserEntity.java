package com.football.fans.place.entity;

import com.football.fans.place.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = false)
public class UserEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String userName;//用户名

    private String password;//用户密码

    private String mobile;//手机号

    private String sex;//性别

    private Integer age;//年龄

    private Integer errorLoginCount;//错误登录次数

    private Date lastLoginTime;//上次登录时间

    private String userStatus;//用户状态

    private String remark;//备注

    private String msgCode;//短信验证码
}
