package com.company.thread1_8;

import java.util.concurrent.*;

/**
 * 使用线程池计算1-100的和
 */
public class Demo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1、创建线程池
        ExecutorService es= Executors.newFixedThreadPool(1);
        //2、提交任务Future:表示将要执行完任务的结果
       Future<Integer> future= es.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName()+"开始计算");
                int sum=0;
                for(int i=1;i<=100;i++){
                    sum+=i;
                    Thread.sleep(10);
                }
                return sum;
            }
        });

       //3、获取结果
        System.out.println(future.get());

        //关闭线程池,等待任务执行完毕才会返回
        es.shutdown();

    }
}
