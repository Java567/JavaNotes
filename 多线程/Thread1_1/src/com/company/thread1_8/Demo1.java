package com.company.thread1_8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示线程池的创建
 * Executor:线程池的根接口，execute()
 * ExecutorService:包含管理线程池的一些方法,submit shutdown
 *     ThreadPoolExecutor
 *        ScheduledThreadPoolExecutor
 * Executors: 创建线程池的工具类
 *        1)创建固定线程个数线程池
 *        2)创建缓存线程池，由任务的多少决定
 *        3)创建单线程池
 *        4)创建调度线程池 调度：周期、定时执行
 */
public class Demo1 {
    public static void main(String[] args) {
        //1.1、创建固定线程个数的线程池
      //ExecutorService es =Executors.newFixedThreadPool(4);
        //1.2、创建缓存线程池 线程个数是由任务个数决定的
       ExecutorService es= Executors.newCachedThreadPool();
        //1.3、创建单线程线程池
        //Executors.newSingleThreadExecutor();
        //1.4、创建调度线程池 调度：周期、定时执行
        //Executors.newScheduledThreadPool()
        //2、创建任务
        Runnable runnable=new Runnable() {
            private int ticket=100;
            @Override
            public void run() {
                while (true){
                    if(ticket<=0){
                        break;
                    }
                    System.out.println(Thread.currentThread().getName()+"买了第"+ticket+"张票");
                    ticket--;
                }
            }
        };
        //3、提交任务
        for(int i=0;i<4;i++){
            es.submit(runnable);
        }
        //4、关闭线程池
        es.shutdown();//等待所有任务执行完毕，然后关闭线程池，不接受新的任务
    }
}
