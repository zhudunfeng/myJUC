package com.adun.test_AQS.test_LockSuppurt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhudunfeng
 * @date 2021/3/16 17:30
 * 以下是异常情况：
 * 2 await方法和notify方法，两个都去掉同步代码块后看运行效果
 *  2.1 异常情况
 *  2.2结论
 *  ReentrantLock的Condition中的await()、signal()、signalAll()用于线程等待和唤醒的方法，都必须在lock于unlock内部使用
 *
 *
 *  3 将signal放在await方法前先执行，t1先signal了，3秒种后t2线程再执行await方法
 *  3.1程序一直无法结束
 *  3.2结论
 *  先await后signal()、signalAll()方法，等待中的线程才会被唤醒，否则无法唤醒
 */
public class AwaitSignalDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
             try {TimeUnit.SECONDS.sleep(3L);} catch (InterruptedException e) { e.printStackTrace();}
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"\tcome in------");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t被唤醒=====");
            } finally {
                lock.unlock();
            }

        },"A").start();


        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"\t通知------");
                condition.signal();
            } finally {
                lock.unlock();
            }
        },"B").start();
    }
}

