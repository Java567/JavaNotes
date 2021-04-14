package com.mage.login.demo.service;

import com.mage.login.demo.entity.User;

public interface UserService {
    public User login(String loginId,String loginPwd);
}
