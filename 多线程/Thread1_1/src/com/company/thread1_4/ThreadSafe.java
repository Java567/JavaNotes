package com.company.thread1_4;

import java.util.Arrays;

/**
 * 线程安全问题
 * 两个线程同时向一个数组中存放数据
 */
public class ThreadSafe {
    private static int index=0;
    public static void main(String[] args) throws InterruptedException {
        //创建数组
        String[] s=new String[5];
        //创建两个操作
        Runnable runnableA=new Runnable() {
            @Override
            public void run() {
                //同步代码块，加锁
                synchronized (s){
                    s[index]="hello";
                    index++;
                }
            }
        };
        Runnable runnableB=new Runnable() {
            @Override
            public void run() {
                synchronized (s){
                    s[index]="world";
                    index++;
                }
            }
        };


        //创建两个线程对象
        Thread a=new Thread(runnableA,"A");
        Thread b=new Thread(runnableB,"B");
        a.start();
        b.start();

        a.join();
        b.join();

        System.out.println(Arrays.toString(s));

    }
}
