package com.adun.handwritten.lock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ADun
 * @date 2022/8/10 22:07
 */
public class AdunReentranLock {

    //线程安全计数器 底层基于cas实现
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public Thread lockThread = null;

    //重入次数
    private int reentry;

    /**
     * 锁的状态值 0 ------当前这把锁没有其他线程 获取到
     * 锁的状态值 1 ------当前这把锁被其他线程获取到
     */
    public void lock() {
        //lock方法设计一把锁  锁状态
        //调用lock方法状态值 0--->1
        //如果改失败的情况下 说明其他线程占有锁
        //实现可重入
        if (Thread.currentThread() == lockThread) {
            //如果当前线程已经获取到了锁
            reentry++;
            return;
        }
        for (; ; ) {
            // cas 失败一直不断重试  缺陷 cpu飙高
            if (atomicInteger.compareAndSet(0, 1)) {
                lockThread = Thread.currentThread();
                reentry++;
                return;
            }
        }
    }

    public void unlock() {
        //当前线程没有获取到锁，直接返回
        if (Thread.currentThread() != lockThread) {
            return;
        }

        //调用了几次lock方法就需要调用几次unLock
        reentry--;
        if (reentry == 0) {
            //cas 状态==0
            for (; ; ) {
                //调用unlock方法状态值 1->0
                if (atomicInteger.compareAndSet(1, 0)) {
                    return;
                }
            }
        }
    }


    public static void main(String[] args) {
//        ReentrantLock reentrantLock = new ReentrantLock();
//        reentrantLock.lock();
        AdunReentranLock adunReentranLock = new AdunReentranLock();
        new Thread(() -> {
            System.out.println(1);
            adunReentranLock.lock();
            System.out.println(2);
            try {
                Thread.sleep(4000);
                System.out.println(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            adunReentranLock.unlock();
        }, "A").start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adunReentranLock.lock();
        System.out.println(3);
        adunReentranLock.unlock();
    }

//    public static void main(String[] args) {
//        AdunReentranLock adunReentranLock = new AdunReentranLock();
//        adunReentranLock.lock();
//        adunReentranLock.lock();
//        System.out.println(111);
//    }

//    public static void main(String[] args) {
//        AdunReentranLock adunReentranLock = new AdunReentranLock();
//        adunReentranLock.lock();
//        adunReentranLock.lock();
//        System.out.println(111);
//        adunReentranLock.unlock();
//        //adunReentranLock.unlock();
//
//        new Thread(() -> {
//            System.out.println(Thread.currentThread() + "开始获取锁");
//            adunReentranLock.lock();
//        }, "A").start();
//    }


}
