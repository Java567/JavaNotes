package com.company.thread1_8;

import java.util.concurrent.*;

/**
 * 使用两个线程，并发计算1-50，51-100的和，再进行汇总统计
 */
public class Demo4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1、创建线程池
        ExecutorService es= Executors.newFixedThreadPool(2);
        Future<Integer> a= es.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int a=0;
                for (int i=1;i<=50;i++){
                    a+=i;
                }
                System.out.println("1-50计算完毕");
                return a;
            }
        });
        Future<Integer> b=es.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int b=0;
                for(int i=51;i<=100;i++){
                    b+=i;
                }
                System.out.println("51-100计算完毕");
                return b;
            }
        });
        Integer sum=a.get()+b.get();
        System.out.println("结果是: "+sum);
        es.shutdown();
    }
}
