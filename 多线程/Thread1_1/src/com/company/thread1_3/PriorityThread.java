package com.company.thread1_3;

public class PriorityThread extends Thread {
    @Override
    public void run() {
        for(int i=0;i<50;i++){
            System.out.println(Thread.currentThread().getName()+"==========="+i);
        }
    }
}
