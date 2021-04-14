package com.company.thread1_3;

public class TestDaemon {
    public static void main(String[] args) {
        DaemonThread d1=new DaemonThread();
        //设置线程为守护线程
        d1.setDaemon(true);
        d1.start();


        for(int i=0;i<10;i++){
            System.out.println("主线程：--------"+i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
