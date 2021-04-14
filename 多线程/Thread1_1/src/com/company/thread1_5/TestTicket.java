package com.company.thread1_5;




public class TestTicket {
    public static void main(String[] args) {
        //1、创建票对象

        Ticket ticket=new Ticket();
        //2、创建线程对象
        Thread w1=new Thread(ticket,"窗口1");
        Thread w2=new Thread(ticket,"窗口2");
        Thread w3=new Thread(ticket,"窗口3");
        Thread w4=new Thread(ticket,"窗口4");
        //3、启动线程
        w1.start();
        w2.start();
        w3.start();
        w4.start();
    }
}
