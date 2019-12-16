package com.atguigu.day03_printThree;

import java.util.concurrent.CountDownLatch;

public class numberDown {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(7);
        for (int i = 1; i <=10 ; i++) {
                    new Thread(()->{

                    },String.valueOf(i)).start();
                }
    }
}
