package com.company.thread1_1;

public class TestThread {
    public static void main(String[] args) {
        //1、创建线程对象
        MyThread myThread=new MyThread("我的子线程1");
        //修改线程名称
         //myThread.setName("我的子线程1");
        //2、启动线程，不能用run方法
        myThread.start();

        MyThread myThread1=new MyThread("我的子线程2");
        //修改线程名称
        //myThread1.setName("我的子线程2");
        myThread1.start();

        //3、主线程执行
        for(int i=0;i<50;i++){
            System.out.println("主线程========="+i);
        }
    }
}
