package com.company.thread1_5;

public class Ticket implements Runnable {
    private static int ticket=100;//100张票

    //创建锁
   // private Object obj=new Object();

    @Override
    public void run() {
        while(true){
            if(!sale()){
                break;
            }
        }
    }

//    //卖票(同步方法)
//    public synchronized boolean sale(){//锁this  如果是静态方法锁Ticket.class
//        if (ticket <= 0) {
//            return false;
//        }
//        System.out.println(Thread.currentThread().getName() + " 买了第" + ticket + "张票");
//        ticket--;
//        return true;
//    }
    //卖票(同步方法)
    public static boolean sale(){//锁this  如果是静态方法锁Ticket.class
        synchronized (Ticket.class) {
            if (ticket <= 0) {
                return false;
            }
            System.out.println(Thread.currentThread().getName() + " 买了第" + ticket + "张票");
            ticket--;
            return true;
        }
    }
}
