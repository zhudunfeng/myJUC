package com.adun.test_AQS.test_ReEnterLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhudunfeng
 * @date 2021/3/15 22:57
 */
public class ReEnterReentrantLockDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(()->{
            lock.lock();
            lock.lock();
            try {
                System.out.println("====外层");
                lock.lock();
                try {
                    System.out.println("====内层");
                } finally {
                    lock.unlock();
                }
            } finally {
                //这里故意注释，实现加锁次数和释放次数不一样
                //由于加锁次数和释放次数不一样，第二个线程始终无法获取到锁，导致一直等待
//                lock.unlock();
                lock.unlock();//正常情况下，我们加锁几次就要释放几次
            }

        },"A").start();


        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"====进入");
            } finally {
                lock.unlock();
            }
        },"B").start();




    }
}
