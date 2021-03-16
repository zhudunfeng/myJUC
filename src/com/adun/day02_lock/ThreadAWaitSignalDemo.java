package com.adun.day02_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Cake_up01{
    private Integer cake=0;

    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while(cake!=0){
                //this.wait();
                condition.await();
            }
            //操作
            ++cake;
            System.out.println(Thread.currentThread().getName()+":"+cake);
            //通知
            //notifyAll();
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
    

    public synchronized void div() throws InterruptedException {
        lock.lock();
        try {
            while (cake==0){
                //this.wait();
                condition.await();
            }
            --cake;
            System.out.println(Thread.currentThread().getName()+":"+cake);
            //notifyAll();
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零
 *
 * 1.线程操作资源类
 * 2.判断/干活/通知
 * 3.使用while（true）会将睡眠的线程重新进行判断
 *
 */
public class ThreadAWaitSignalDemo{
    public static void main(String[] args) throws InterruptedException {
        Cake_up01 eatCake = new Cake_up01();
        new Thread(()->{
            try {
                for (int i = 1; i <=100; i++) {
                    eatCake.add();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                for (int i = 1; i <=100; i++) {
                    eatCake.div();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
       // TimeUnit.SECONDS.sleep(3);
        new Thread(()->{
            try {
                for (int i = 1; i <=100; i++) {
                    eatCake.add();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
        //TimeUnit.SECONDS.sleep(3);
        new Thread(()->{
            try {
                for (int i = 1; i <=100; i++) {
                    eatCake.div();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();
    }
}
