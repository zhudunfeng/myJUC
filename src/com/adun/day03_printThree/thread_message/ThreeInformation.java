package com.adun.day03_printThree.thread_message;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
    private int flag = 1;
    //锁
    private Lock lock = new ReentrantLock();
    //三把钥匙
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print5() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (flag != 1) {
                c1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "/" + i);
            }
            //通知
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print10() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (flag != 2) {
                c2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "/" + i);
            }
            //通知
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print15() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (flag != 3) {
                c3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "/" + i);
            }
            //通知
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 精准通知
 * 1.在高内聚低耦合的条件下，线程操作资源类
 * 2.判断/干活/通知
 * 3.使用while进行判断，解决虚假唤醒的bug
 * 4.修改标志位
 */
public class ThreeInformation {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    shareData.print5();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    shareData.print10();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    shareData.print15();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    shareData.print5();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    shareData.print10();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    shareData.print15();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();


    }
}
