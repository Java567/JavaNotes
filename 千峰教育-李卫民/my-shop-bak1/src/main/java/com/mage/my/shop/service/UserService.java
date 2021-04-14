package com.mage.my.shop.service;

import com.mage.my.shop.entity.User;

public interface UserService {

    /**
     * 登录
     * @param email
     * @param password
     * @return
     */
    public User login(String email, String password);
}
