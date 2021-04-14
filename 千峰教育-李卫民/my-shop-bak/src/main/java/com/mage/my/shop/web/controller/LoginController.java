package com.mage.my.shop.web.controller;

import com.mage.my.shop.commons.context.SpringContext;
import com.mage.my.shop.entity.User;
import com.mage.my.shop.service.UserService;
import com.mage.my.shop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    private UserService userService=SpringContext.getBean(UserServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试用的
//        SpringContext context=new SpringContext();
//        UserService userService= (UserService) context.getBean("userService");
//        User admin = userService.login("admin@mage.com", "admin");
//        System.out.println(admin);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email =req.getParameter("email");
        String password=req.getParameter("password");

//        SpringContext context=new SpringContext();
//        UserService userService=(UserService) context.getBean("userService");
        User user = userService.login(email, password);
        //登录成功
        if(user!=null){
            //重定向
            resp.sendRedirect("/main.jsp");
        }

        //登录失败
        else {
            //跳转
            req.setAttribute("message","用户名或密码错误");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }
}
