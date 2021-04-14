package com.mage.my.shop.dao.impl;

import com.mage.my.shop.dao.UserDao;
import com.mage.my.shop.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    private static final Logger logger=LoggerFactory.getLogger(UserDaoImpl.class);

    public User getUserByEmailAndPassword(String email, String password) {
        logger.debug("调用 getUser()方法, email:{} password{}",email,password);
        User user=null;
        if("admin@mage.com".equals(email)){
            if("admin".equals(password)){
                user=new User();
                user.setEmail("admin@mage.com");
                user.setUsername("lijun");

                logger.info("成功获取 \"{}\"的用户信息",user.getUsername());
            }
        }else {
            logger.warn("获取\"{}\"的用户信息失败",email);
        }
        return user;
    }
}


