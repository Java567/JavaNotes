package com.company.thread1_3;

public class TestJoin {
    public static void main(String[] args) {
        JoinThread j1=new JoinThread();
        j1.start();
        try {
            //加入当前线程，并阻塞当前线程（main），直到加入线程执行完毕
            j1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //for 主线程
        for(int i=0;i<20;i++){
            System.out.println(Thread.currentThread().getName()+"========="+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
