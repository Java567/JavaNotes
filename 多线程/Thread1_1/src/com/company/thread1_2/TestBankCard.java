package com.company.thread1_2;

public class TestBankCard {
    public static void main(String[] args) {
        //1、创建一张银行卡
        BankCard card=new BankCard();
        //2、创建存钱,取钱
        AddMoney add=new AddMoney(card);
        SubMoney sub=new SubMoney(card);
        //3、创建两个线程
        Thread chenChen=new Thread(add,"晨晨");
        Thread bingBing=new Thread(sub,"冰冰");
        //4、启动线程
        chenChen.start();
        bingBing.start();

    }
}
