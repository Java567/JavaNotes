package com.company.thread1_6;

public class BankCard {
    //余额
    private double money;
    //标记
    private boolean flag=false;//true 表示有钱可以取钱 false没钱可以存钱

    //存钱
    public synchronized void save(double m){
        while (flag) {//有钱
            try {
                this.wait();//进入等待队列，同时释放锁和cpu
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        money=money+m;
        System.out.println(Thread.currentThread().getName()+"存了"+m+" 余额手机"+money);
        //修改标记
        flag=true;
        //唤醒取钱线程
        this.notifyAll();


    }

    //取钱
    public synchronized void take(double m){//锁代表this
        while (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        money=money-m;
        System.out.println(Thread.currentThread().getName()+"取了"+m+" 余额手机"+money);
        //修改标记
        flag=false;
        //唤醒存钱线程
        this.notifyAll();

    }
}
