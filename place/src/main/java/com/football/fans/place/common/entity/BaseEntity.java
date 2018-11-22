package com.football.fans.place.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BaseEntity<T> implements Serializable {

    private String id;//主键ID

    private Integer pageNo;//跳转页

    private Integer pageSize;//每页显示

    private Integer currRecord;//当前记录

    private String createUserId;//创建人ID

    private String createUser;//创建人

    private Date createTime;//创建时间

    private Integer isDelete;//是否删除

    private String deleteUser;//删除用户

    private String deleteUserId;//删除用户

    private Date deleteTime;//删除日期
}
