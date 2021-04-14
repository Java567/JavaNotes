package com.company.thread1_10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 演示：使用多线程操作线程不安全集合
 */
public class Demo1 {
    public static void main(String[] args) {
        //1、创建arrayList
       // ArrayList<String> arrayList=new ArrayList<>();
        //1.1使用Collections中的线程安全方法转成线程安全的集合
        //List<String> synList= Collections.synchronizedList(arrayList);
        //1.2使用CopyOnWriteArrayList
        CopyOnWriteArrayList<String> arrayList=new CopyOnWriteArrayList<>();
        //2、创建线程
        for(int i=0;i<20;i++){
            int temp=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10;j++){
                        arrayList.add(Thread.currentThread().getName()+"====="+temp+"===="+j);
                        System.out.println(arrayList.toString());
                    }
                }
            }).start();
        }
    }
}
