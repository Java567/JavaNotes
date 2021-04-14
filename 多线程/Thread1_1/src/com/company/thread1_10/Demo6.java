package com.company.thread1_10;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列的使用
 * 案例1：创建一个有界队列，添加数据
 * 案例2：使用阻塞队列实现生产者和消费者
 */
public class Demo6 {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<>(5);
        //添加元素
        queue.put("aaa");
        queue.put("bbb");
        queue.put("ccc");
        queue.put("ddd");
        queue.put("eee");
        //删除元素
        queue.take();
        System.out.println("已经添加了5个元素");
        queue.put("xyz");
        System.out.println("已经添加了6个元素");
        System.out.println(queue.toString());
    }

}
