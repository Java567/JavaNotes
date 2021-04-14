package com.company.thread1_10;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap的使用
 */
public class Demo8 {
    public static void main(String[] args) {
        //1、创建集合
        ConcurrentHashMap<String,String> hashMap=new ConcurrentHashMap<>();
        //2、使用多线程添加数据
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int k=0;k<10;k++){
                        hashMap.put(Thread.currentThread().getName()+"--"+k,k+"");
                        System.out.println(hashMap);
                    }
                }
            }).start();
        }
    }
}
