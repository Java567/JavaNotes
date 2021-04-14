package com.mage.my.shop.dao;

import com.mage.my.shop.entity.User;

public interface UserDao {

    /**
     * 根据邮箱和密码获取用户信息
     * @param email
     * @param password
     * @return
     */
    public User getUserByEmailAndPassword(String email,String password);
}
