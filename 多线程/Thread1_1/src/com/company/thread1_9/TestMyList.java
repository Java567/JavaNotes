package com.company.thread1_9;

import java.util.ArrayList;
import java.util.Arrays;

public class TestMyList {
    public static void main(String[] args) throws InterruptedException {
        MyList list=new MyList();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                list.add("hello");
            }
        };
        Runnable runnable1=new Runnable() {
            @Override
            public void run() {
                list.add("world");
            }
        };

        Thread t1=new Thread(runnable);
        Thread t2=new Thread(runnable1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(Arrays.toString(list.getStr()));

    }
}
