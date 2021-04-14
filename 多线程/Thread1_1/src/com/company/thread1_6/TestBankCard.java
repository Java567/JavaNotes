package com.company.thread1_6;

public class TestBankCard {
    public static void main(String[] args) {
        //1、创建银行卡
        BankCard card=new BankCard();
        //2、创建操作
        AddMoney add=new AddMoney(card);
        SubMoney sub=new SubMoney(card);
        //3、创建线程对象
        Thread a=new Thread(add,"晨晨");
        Thread b=new Thread(sub,"冰冰");
        Thread c=new Thread(add,"明明");
        Thread d=new Thread(sub,"莉莉");
        a.start();
        c.start();
        b.start();
        d.start();
    }
}
