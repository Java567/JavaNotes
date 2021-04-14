package com.company.thread1_9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的使用
 */
public class MyList {
    //创建锁
    private Lock lock=new ReentrantLock();
    private String[] str={"A","B","","",""};
    private int count=2;
    public void add(String value){
        lock.lock();
        try{
            str[count]=value;
            Thread.sleep(1000);
            count++;
            System.out.println(Thread.currentThread().getName()+"添加了"+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String[] getStr() {
        return str;
    }

}
