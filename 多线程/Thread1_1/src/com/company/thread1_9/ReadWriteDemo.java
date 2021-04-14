package com.company.thread1_9;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示读写锁的使用
 * ReentrantReadWriteLock
 */
public class ReadWriteDemo {
    //创建读写锁
    private ReentrantReadWriteLock rrl=new ReentrantReadWriteLock();
    //获取读锁
    private ReentrantReadWriteLock.ReadLock readLock=rrl.readLock();
    //获取写锁
    private ReentrantReadWriteLock.WriteLock writeLock=rrl.writeLock();

    private String value;

    //读锁
    public String getValue(){
        //使用读锁上锁
        readLock.lock();
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("读取："+this.value);
            return this.value;
        }finally {
            readLock.unlock();
        }
    }

    //写锁
    public void setValue(String value) {
        writeLock.lock();
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("写入："+this.value);
            this.value = value;
        }finally {
            writeLock.unlock();
        }
    }
}
