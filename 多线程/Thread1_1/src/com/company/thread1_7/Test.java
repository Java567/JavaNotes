package com.company.thread1_7;

public class Test {
    public static void main(String[] args) {
        //容器
        BreadCon con=new BreadCon();
        //生产和消费
        Product product=new Product(con);
        Consume consume=new Consume(con);
        //创建线程对象
        Thread chenchen=new Thread(product,"晨晨");
        Thread bingbing=new Thread(consume,"冰冰");
        Thread mingming=new Thread(product,"明明");
        Thread lili=new Thread(consume,"莉莉");
        //启动线程
        chenchen.start();
        bingbing.start();
        mingming.start();
        lili.start();
    }
}
