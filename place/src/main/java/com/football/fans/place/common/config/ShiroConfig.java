package com.football.fans.place.common.config;

import com.football.fans.place.common.auth.UserRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ShiroConfig {
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//		//拦截器
        Map<String,String> filterChainMap = new LinkedHashMap<String,String>();
//		//配置不会被拦截的链接，顺序判断
        filterChainMap.put("/static/**", "anon");
        filterChainMap.put("/system/user/registerUser", "anon");
        filterChainMap.put("/system/user/sendSms", "anon");
        filterChainMap.put("/system/user/sendSmsPwd", "anon");
        filterChainMap.put("/system/user/resetPwd", "anon");
//		//配置退出过滤器，其中的具体的退出代码Shiro已经替我们实现了
//		filterChainMap.put("/logout", "logout");
//      	//authc:所有url都必须认证通过才可以访问；anon:所有url都都可以匿名访问
        filterChainMap.put("/**", "authc");
//		//如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/system/login/doLogin");
//		//登录成功后要跳转的链接
//		shiroFilterFactoryBean.setSuccessUrl("/index");
//		//未授权界面;
//		shiroFilterFactoryBean.setUnauthorizedUrl("/404");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    /**
     * @desc 配置安全管理器
     * @author zdw
     * @return
     * @date 2018年11月9日
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    /**
     * @desc 初始化自定义realm
     * @author zdw
     * @return
     * @date 2018年11月9日
     */
    @Bean
    public UserRealm customRealm() {
        UserRealm userRealm = new UserRealm();
//		customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    /**
     * @desc 配置凭证匹配器
     * @author zdw
     * @return
     * @date 2018年11月9日
     */
	/*@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}*/
}
