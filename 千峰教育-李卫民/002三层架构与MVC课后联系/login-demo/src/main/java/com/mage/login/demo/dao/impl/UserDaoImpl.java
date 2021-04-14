package com.mage.login.demo.dao.impl;

import com.mage.login.demo.dao.UserDao;
import com.mage.login.demo.entity.User;

public class UserDaoImpl implements UserDao {

    /**
     * 用户登录
     * @param loginId
     * @param userPwd
     * @return String ：登录结果
     */
    public User login(String loginId, String userPwd) {
        User user=null;

        //防止sql注入
        //直接根据loginId查询出这个用户信息
        if("admin".equals(loginId)){
            //在根据传入的密码匹配
            if("admin".equals(userPwd)){
                user=new User();
                user.setLoginId("admin");
                user.setLoginPwd("admin");
                user.setUsername("李俊");
         //       user.setAge(20);
            }
        }
        return user;
    }
}
