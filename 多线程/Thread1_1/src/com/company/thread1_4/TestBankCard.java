package com.company.thread1_4;

public class TestBankCard {
    public static void main(String[] args) {
        //1、创建银行卡
        BankCard card=new BankCard();
        //2、创建两个操作
        //存钱
        Runnable add=new Runnable() {
            @Override
            public void run() {
                synchronized (card) {
                    for (int i = 0; i < 10; i++) {
                        card.setMoney(card.getMoney() + 1000);
                        System.out.println(Thread.currentThread().getName() + "存了1000，余额时：" + card.getMoney());
                    }
                }
            }
        };
        //取钱
        Runnable sub=new Runnable() {
            @Override
            public void run() {
                synchronized (card) {
                    for (int i = 0; i < 10; i++) {
                        if (card.getMoney() >= 1000) {
                            card.setMoney(card.getMoney() - 1000);
                            System.out.println(Thread.currentThread().getName() + "取了1000，余额时：" + card.getMoney());
                        } else {
                            System.out.println("余额不足，请存钱");
                            i--;
                        }
                    }
                }
            }
        };

        Thread threadA=new Thread(add,"晨晨");
        Thread threadB=new Thread(sub,"冰冰");

        threadA.start();
        threadB.start();

    }
}
