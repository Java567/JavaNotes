package com.company.thread1_8;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 演示Callable的使用
 * Callable和Runnable接口的区别
 *   1)Callable接口中call方法由返回值,Runnable接口run方法没有返回值
 *   2)Callable接口中call方法有声明异常,Runnable接口中run方法没有异常
 */
public class Demo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //功能需求,使用Callable实现1-100和
        //1、创建Callable对象
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
              System.out.println(Thread.currentThread().getName()+"开始计算");
              int sum=0;
              for(int i=1;i<=100;i++){
                  sum+=i;
                  Thread.sleep(100);
              }
              return sum;
            }
        };

        //2、把Callable对象转成可执行任务
        FutureTask<Integer> task=new FutureTask<>(callable);

        //3、创建线程
        Thread thread=new Thread(task);
        //4、启动线程
        thread.start();

        //5、获取结果(等待call方法执行完毕，才会返回)
        Integer sum=task.get();
        System.out.println("结果是: "+sum);
    }

}
