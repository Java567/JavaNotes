package com.company.thread1_4;

public class Ticket implements Runnable {
    private int ticket=100;//100张票

    //创建锁
    private Object obj=new Object();

    @Override
    public void run() {
        while(true){
            synchronized (obj) {
                if (ticket <= 0) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " 买了第" + ticket + "张票");
                ticket--;
            }
        }
    }
}
