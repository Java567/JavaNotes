package com.mage.login.demo.dao;

import com.mage.login.demo.entity.User;

public interface UserDao {

    public User login(String loginId, String userPwd);

}
