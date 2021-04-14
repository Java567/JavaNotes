package com.company.thread1_10;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue的使用
 */
public class Demo5 {
    public static void main(String[] args) throws InterruptedException {
        //1、创建安全队列
        ConcurrentLinkedQueue<Integer> queue=new ConcurrentLinkedQueue<>();
        //2、入队操作
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<=5;i++) {
                    queue.offer(i);
                }
            }
        });
        //2、入队操作
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=6;i<=10;i++) {
                    queue.offer(i);
                }
            }
        });
        //3、启动线程
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //4、出队操作
        int size=queue.size();
        for(int i=0;i<size;i++){
            System.out.println(queue.poll());
        }
    }
}
