package com.mage.my.shop.web.admin.service;

import com.mage.my.shop.domain.TbUser;
import com.mage.my.shop.web.admin.dao.TbUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void queryAll() {
        List<TbUser> tbUsers=tbUserService.selectAll();
        for(TbUser tbUser:tbUsers){
            System.out.println(tbUser.getUsername());
        }
    }


    @Test
    public void Insert(){
        TbUser tbUser=new TbUser();
        tbUser.setUsername("lijun");
        tbUser.setPhone("1588888888");
        tbUser.setEmail("123@qq.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserService.save(tbUser);
    }

    public void testMD5(){
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

    @Test
    public void delete(){
        tbUserService.delete(38L);
    }

    @Test
    public void getById(){
       TbUser tbUser= tbUserService.getById(36L);
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void update(){
        TbUser tbUser=new TbUser();
        tbUser.setUsername("lijun");
        tbUser.setUpdated(new Date());
        tbUser.setPassword("113234234");
        tbUser.setEmail("123@qq.com");
        tbUser.setPhone("12435436457");
        tbUser.setId(36L);
        tbUserService.update(tbUser);
    }

    @Test
    public void selectByUsername(){
        String username="uni";
        List<TbUser> tbUserList=tbUserService.selectByUsername(username);
        for(TbUser tbUser:tbUserList){
            System.out.println(tbUser.getUsername());
        }
    }
}