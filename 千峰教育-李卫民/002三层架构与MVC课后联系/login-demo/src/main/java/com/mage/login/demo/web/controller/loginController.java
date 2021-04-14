package com.mage.login.demo.web.controller;

import com.mage.login.demo.entity.User;
import com.mage.login.demo.service.UserService;
import com.mage.login.demo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录控制器
 */
public class loginController extends HttpServlet {

    private UserService userService=new UserServiceImpl();


    /**
     *get和post的区别
     * 1、
     * get传参数是明文的 参数大小有限制 4kb
     * post传参数是秘文的  参数大小无限制
     * 2、get参数是？形式
     * 3、post可以上传文件，get不行
     * 1、语义区别
     * get只用于获取数据
     * post只用于提交数据
     *
     * post请求是两次，get是一次
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginId = req.getParameter("loginId");
        String loginPwd = req.getParameter("loginPwd");
        User user = userService.login(loginId, loginPwd);
        //登录的处理
        if(user==null){
            req.getRequestDispatcher("/fail.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("/success.jsp").forward(req,resp);
        }

    }
}
