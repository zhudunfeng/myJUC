package com.adun.test_AQS;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhudunfeng
 * @date 2021/3/23 23:01
 */
public class AQSDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        //带入一个银行办理业务的案例来模拟我们的AQS是如何进行线程的管理和通知唤醒机制

        //3个线程来模拟3个来银行网点，受理窗口办理业务的顾客

        //A顾客就是第一个顾客，此时窗口没有任何人，A可以直接去办理
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("--------A thread come in");
                //暂停几秒[几分钟]
                 try {TimeUnit.MINUTES.sleep(20);} catch (InterruptedException e) { e.printStackTrace();}
            } finally {
                lock.unlock();
            }

        },"A").start();


        //第二个顾客，第二个线程---》由于受理窗口只有一个(只能一个线程持有锁)，此时B只能等待
        //进入候客区
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("--------B thread come in");
                //暂停几秒[几分钟]
                try {TimeUnit.MINUTES.sleep(20);} catch (InterruptedException e) { e.printStackTrace();}
            } finally {
                lock.unlock();
            }

        },"B").start();

        //第3个顾客，第3个线程---》由于受理窗口只有一个(只能一个线程持有锁)，此时C只能等待
        //进入候客区
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("--------C thread come in");
                //暂停几秒[几分钟]
                try {TimeUnit.MINUTES.sleep(20);} catch (InterruptedException e) { e.printStackTrace();}
            } finally {
                lock.unlock();
            }

        },"C").start();

    }
}
