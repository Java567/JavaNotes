package com.company.thread1_10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue接口的使用
 */
public class Demo4 {
    public static void main(String[] args) {
        //1、创建队列
        Queue<String> queue=new LinkedList<>();
        //2、入队
        queue.offer("苹果");
        queue.offer("橘子");
        queue.offer("葡萄");
        queue.offer("西瓜");
        queue.offer("榴莲");
        System.out.println(queue.peek());
        System.out.println("------------");
        //3、出队
        System.out.println("元素个数："+queue.size());
        int size =queue.size();
        for(int i=0;i<size;i++){
            System.out.println(queue.poll());
        }
    }
}
