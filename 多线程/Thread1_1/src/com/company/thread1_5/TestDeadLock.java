package com.company.thread1_5;

public class TestDeadLock {
    public static void main(String[] args) {
        Boy boy=new Boy();
        Girl girl=new Girl();
        boy.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        girl.start();
    }
}
