package com.company.thread1_10;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 演示CopyOnWriteArraySet
 * 重复依据，equals方法
 */
public class Demo3 {
    public static void main(String[] args) {
        //1、创建集合
        CopyOnWriteArraySet<String> set=new CopyOnWriteArraySet<>();
        //2、添加元素
        set.add("pingguo");
        set.add("huawei");
        set.add("xiaomi");
        set.add("lianxiang");
        set.add("pingguo");
        //3、打印
        System.out.println("元素个数："+set.size());
        System.out.println(set.toString());
    }
}
