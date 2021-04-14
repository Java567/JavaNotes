package com.mage.my.shop.commons.context;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class SpringContext implements ApplicationContextAware, DisposableBean {

    //context 并没有跟着项目启动初始化
//    public static ApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");


//    private static ApplicationContext context;


    //第一种方法
//    public Object getBean(String beanId){
//        ApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
//        return context.getBean(beanId);
//    }

    private static final Logger logger= LoggerFactory.getLogger(SpringContext.class);

    private static ApplicationContext applicationContext;

    /**
     * 使用ApplicationContext,根据beanId获取实例
     * @param beanId
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanId){
        assertContextInjected();
        return (T) applicationContext.getBean(beanId);
    }

    /**
     * 使用ApplicationContext,根据class获取实例
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        assertContextInjected();
        return applicationContext.getBean(clazz);

    }

    //第二种wen整合spring
    public void destroy() throws Exception {
        logger.debug("清空 ApplicationContext");
        applicationContext=null;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext=applicationContext;
    }

    private static void assertContextInjected(){
        //判断是否为空
        Validate.validState(applicationContext==null,"你还没有在spring-context.xml 中配置 SpringContext 对象");
    }

}
