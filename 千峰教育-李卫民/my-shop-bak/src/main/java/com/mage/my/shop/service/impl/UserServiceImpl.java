package com.mage.my.shop.service.impl;

import com.mage.my.shop.commons.context.SpringContext;
import com.mage.my.shop.dao.UserDao;
import com.mage.my.shop.entity.User;
import com.mage.my.shop.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao=SpringContext.getBean("userDao");

    public User login(String email, String password) {
//        SpringContext context=new SpringContext();
//        UserDao userDao=(UserDao) context.getBean("userDao");
        return userDao.getUserByEmailAndPassword(email,password);
    }
}
