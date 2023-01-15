package com.example.shirospringboot.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiucai
 * @date 2023/1/15 21:34
 * @description
 */
@Configuration
public class ShiroConfig {

    /**
     * 第三步：创建ShiroFilterFactoryBean
     */
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean  = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        return bean;
    }

    /**
     * 第二步：创建DefaultWebSecurityManager(设置安全管理器)
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("UserRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 第一步：创建realm对象(用户相关的)
     */
    @Bean("UserRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
