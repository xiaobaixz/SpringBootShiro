package com.example.shirospringboot.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

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
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean  = new ShiroFilterFactoryBean();
        //设置安全过滤器
        bean.setSecurityManager(securityManager);

        /*
          anno: 无需认证就可以访问
          authc: 必须认证了才能访问
          user: 必须拥有 记住我 功能才能用(一般不用这个方法)
          perms: 拥有对某个资源的权限才能访问
          role: 拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);

        bean.setLoginUrl("/toLogin");

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
