package com.football.fans.sso.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户实体类
 * @author itdragon
 *
 */
@Table(name="t_user")
@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;						// 自增长主键
	private String userId;//用户ID
	private String userName;//用户名
	private String loginName;//登录名
	private String password;//密码
	private String policeId;//工号
	private String post;//职务
	private String station;//岗位
	private String mobile;//电话号码
	private String telephone;//固定电话
	private String email;//邮件地址
	private String caNO;//CA号
	private String comments;//备注
	private Integer status;//

	@Transient
	private String plainPassword; 			// 登录时的密码，不持久化到数据库
	private String salt;					// 用于加密的盐
	private String iphone;					// 手机号
	private String platform;				// 用户来自的平台
	private String createdDate;				// 用户注册时间
	private String updatedDate;				// 用户最后一次登录时间
	private String account;					// 登录的账号

	/*private String account;					// 登录的账号
	private String userName;				// 注册的昵称
	@Transient
	private String plainPassword; 			// 登录时的密码，不持久化到数据库
	private String password;				// 加密后的密码
	private String salt;					// 用于加密的盐
	private String iphone;					// 手机号
	private String email;					// 邮箱
	private String platform;				// 用户来自的平台
	private String createdDate;				// 用户注册时间
	private String updatedDate;				// 用户最后一次登录时间
	
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", userName=" + userName + ", plainPassword=" + plainPassword
				+ ", password=" + password + ", salt=" + salt + ", iphone=" + iphone + ", email=" + email
				+ ", platform=" + platform + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}*/
}
