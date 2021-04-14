package com.company.thread1_2;

public class Ticket implements Runnable {
    private int ticket=100;//100张票

    @Override
    public void run() {
        while(true){
            if(ticket<=0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+" 买了第"+ticket+"张票");
            ticket--;
        }
    }
}
