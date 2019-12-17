package com.atguigu.day04_threadPool;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Cake_up01 {
    private Integer cake = 0;

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (cake != 0) {
                //this.wait();
                c1.await();
            }
            //操作
            ++cake;
            System.out.println(Thread.currentThread().getName() + ":" + cake);
            //通知
            //notifyAll();
            c2.signal();
        } finally {
            lock.unlock();
        }
    }


    public void div() throws InterruptedException {
        lock.lock();
        try {
            while (cake == 0) {
                //this.wait();
                c2.await();
            }
            --cake;
            System.out.println(Thread.currentThread().getName() + ":" + cake);
            //notifyAll();
            c1.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零
 * <p>
 * 1.线程操作资源类
 * 2.判断/干活/通知
 * 3.使用while（true）会将睡眠的线程重新进行判断
 */
public class ThreadPoolAWaitSignalDemo {
    public static void main(String[] args) {
        Cake_up01 eatCake = new Cake_up01();
        //ExecutorService threadPool=Executors.newFixedThreadPool(5);

        ExecutorService executorService = new ThreadPoolExecutor(
                //核心常驻线程
                5,
                //线程池最大线程数
                10,
                //线程空闲存活时间
                500,
                //存活时间单位
                TimeUnit.MILLISECONDS,
                //阻塞队列
                new LinkedBlockingQueue<Runnable>(5),
                //线程工厂
                Executors.defaultThreadFactory(),
                //拒绝策略
                new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 1; i <= 10; i++) {
                executorService.execute(() -> {
                    try {
                        eatCake.add();
                        eatCake.div();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } finally {
            executorService.shutdown();
        }
    }
}
