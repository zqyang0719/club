package com.football.fans.sso.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.football.fans.sso.pojo.ResultInfo;
import com.football.fans.sso.pojo.User;
import com.football.fans.sso.repository.JedisClient;
import com.football.fans.sso.repository.UserRepository;
import com.football.fans.sso.utils.CookieUtils;
import com.football.fans.sso.utils.CommonUtils;
import com.football.fans.sso.utils.JsonUtils;

@Service
@Transactional
@PropertySource(value = "classpath:redis.properties")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    public ResultInfo registerUser(User user) {
        // 检查用户名是否注册，一般在前端验证的时候处理，因为注册不存在高并发的情况，这里再加一层查询是不影响性能的
        if (null != userRepository.findByAccount(user.getAccount())) {
            return ResultInfo.build(400, "");
        }
        userRepository.save(user);
        // 注册成功后选择发送邮件激活。现在一般都是短信验证码
        return ResultInfo.build(200, "");
    }

    public ResultInfo userLogin(String account, String password,
                                HttpServletRequest request, HttpServletResponse response) {
        // 判断账号密码是否正确
        User user = userRepository.findByAccount(account);
        if (!CommonUtils.decryptPassword(user, password)) {
            return ResultInfo.build(400, "账号名或密码错误");
        }
        // 生成token
        String token = UUID.randomUUID().toString();
        // 清空密码和盐避免泄漏
        String userPassword = user.getPassword();
        String userSalt = user.getSalt();
        user.setPassword(null);
        user.setSalt(null);
        // 把用户信息写入 redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        // user 已经是持久化对象了，被保存在了session缓存当中，若user又重新修改了属性值，那么在提交事务时，此时 hibernate对象就会拿当前这个user对象和保存在session缓存中的user对象进行比较，如果两个对象相同，则不会发送update语句，否则，如果两个对象不同，则会发出update语句。
        user.setPassword(userPassword);
        user.setSalt(userSalt);
        // 设置 session 的过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 添加写 cookie 的逻辑，cookie 的有效期是关闭浏览器就失效。
        CookieUtils.setCookie(request, response, "USER_TOKEN", token);
        // 返回token
        return ResultInfo.ok(token);
    }

    public void logout(String token) {
        jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
    }

    public ResultInfo queryUserByToken(String token) {
        // 根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        // 判断是否为空
        if (StringUtils.isEmpty(json)) {
            return ResultInfo.build(400, "此session已经过期，请重新登录");
        }
        // 更新过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 返回用户信息
        return ResultInfo.ok(JsonUtils.jsonToPojo(json, User.class));
    }

}
