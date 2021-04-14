package com.mage.login.demo.service.impl;

import com.mage.login.demo.dao.UserDao;
import com.mage.login.demo.dao.impl.UserDaoImpl;
import com.mage.login.demo.entity.User;
import com.mage.login.demo.service.UserService;

public class UserServiceImpl implements UserService {
    //数据访问层的具体实现
    private UserDao userDao=new UserDaoImpl();

    public User login(String loginId, String loginPwd) {
        return userDao.login(loginId,loginPwd);
     }
}
