package com.mage.my.shop.web.controller;


import com.mage.my.shop.commons.constant.ConstantUtils;
import com.mage.my.shop.entity.User;
import com.mage.my.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = {"login",""},method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    /**
     * 登录逻辑
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password , HttpServletRequest httpServletRequest){
        User user=userService.login(email,password);

        //登录失败
        if(user==null){
           return login();
        }

        //登录成功
        else {
            //将登录信息放入会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,user);
            //重定向
            return "redirect:/main";
        }
    }


}
