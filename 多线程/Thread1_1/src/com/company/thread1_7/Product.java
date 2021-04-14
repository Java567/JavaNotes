package com.company.thread1_7;

public class Product implements Runnable {

    private BreadCon con;

    public Product(BreadCon con) {
        super();
        this.con = con;
    }

    @Override
    public void run() {
        for(int i=0;i<30;i++){
            con.input(new Bread(i,Thread.currentThread().getName()));
        }
    }
}
