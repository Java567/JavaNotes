package com.company.thread1_2;

/**
 * 存钱
 */
public class AddMoney implements Runnable{

    private BankCard card;
    public AddMoney(BankCard card){
        this.card=card;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            card.setMoney(card.getMoney()+1000);
            System.out.println(Thread.currentThread().getName()+"存了1000块钱,余额是: "+card.getMoney());
        }
    }
}
