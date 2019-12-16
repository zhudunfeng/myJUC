package com.atguigu.day01_saletickets;

/**
 * 3 售票员 卖出 30
 */
public class SalePicket {

    public static void main(String[] args) {
      /*  com.atguigu.day01_saletickets.TestThread t1 = new com.atguigu.day01_saletickets.TestThread();
        com.atguigu.day01_saletickets.TestThread t2 = new com.atguigu.day01_saletickets.TestThread();
        com.atguigu.day01_saletickets.TestThread t3 = new com.atguigu.day01_saletickets.TestThread();*/
        Thread t1 = new Thread(new MyThread());
        Thread t2 = new Thread(new MyThread());
        Thread t3 = new Thread(new MyThread());
        t1.start();
        t2.start();
        t3.start();
    }
}
