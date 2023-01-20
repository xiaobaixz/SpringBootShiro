package com.example.shirospringboot.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author xiucai
 * @date 2023/1/15 21:36
 * @description
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 授权的方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权的=>doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");

        //获取当前登录的对象(下面可以根据数据库获取动态的权限，User currentUser = (User) subject.getPrincipal()可以用来获取全局的用户信息)
        //Subject subject = SecurityUtils.getSubject();
        //
        //User currentUser = (User) subject.getPrincipal();
        //info.addStringPermission(currentUser.getPrams());

        return info;
    }

    /**
     * 认证的方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证的=>doGetAuthenticationInfo");

        //用户名、密码
        String username = "root";
        String password = "123456";

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        if (!userToken.getUsername().equals(username)) {
            //这里shiro框架会自动帮助我们抛出 UnKnownAccountException
            return null;
        }

        //这里可以利用shiro进行对密码加密，有md5加密、md5加盐加密
        return new SimpleAuthenticationInfo("", password, "");
        //return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
