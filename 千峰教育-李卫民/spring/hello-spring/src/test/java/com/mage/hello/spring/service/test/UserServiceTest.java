package com.mage.hello.spring.service.test;

import com.mage.hello.spring.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService;

    @Before
    public void before(){
        System.out.println("初始化数据库连接");
        ApplicationContext applicationContext= (ApplicationContext) new ClassPathXmlApplicationContext("spring-context.xml");
        userService= (UserService) applicationContext.getBean("userService");

    }

    @Test
    public void testSayHi(){
        userService.sayHi();
    }

    @Test
    public void testDayHello(){
        System.out.println("Hello Junit");
    }

//    /**
//     * 测试断言
//     */
//    @Test
//    public void testAssert() {
//        String obj1 = "junit";
//        String obj2 = "junit";
//        String obj3 = "test";
//        String obj4 = "test";
//        String obj5 = null;
//        int var1 = 1;
//        int var2 = 2;
//        int[] arithmetic1 = {1, 2, 3};
//        int[] arithmetic2 = {1, 2, 3};
//
//        assertEquals(obj1, obj2);
//
//        assertSame(obj3, obj4);
//
//        assertNotSame(obj2, obj4);
//
//        assertNotNull(obj1);
//
//        assertNull(obj5);
//
//        assertTrue("为真", var1 == var2);
//
//        assertArrayEquals(arithmetic1, arithmetic2);
//    }


    @After
    public void after(){
        System.out.println("关闭数据库连接");
    }
}
