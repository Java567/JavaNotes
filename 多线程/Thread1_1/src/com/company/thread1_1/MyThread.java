package com.company.thread1_1;

/**
 * 线程类
 */
public class MyThread extends Thread {

    public MyThread(){

    }

    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            //this.getId()获取线程Id
            //this.getName()获取线程名称
            //第一种方式this.getId()和this.getName()
            //System.out.println("线程id:"+this.getId()+" 线程名称"+this.getName()+" 子线程........"+i);

            //第二种方式获取线程id和线程名称，Thread.currentThread() 获取当前线程
            System.out.println("线程id:"+Thread.currentThread().getId()+" 线程名称："+Thread.currentThread().getName()+" 子线程........"+i);
        }
    }
}
