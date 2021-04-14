package com.company.thread1_1;

/**
 * 卖票窗口类(线程类)
 */
public class TicketWin extends Thread{

    public TicketWin(){

    }
    public TicketWin(String name){
        super(name);
    }

    private int ticket=100;//票

    @Override
    public void run() {
        //卖票功能
        while(true){
            if(ticket==0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"卖了第"+ticket+"张票");
            ticket--;
        }
    }
}
