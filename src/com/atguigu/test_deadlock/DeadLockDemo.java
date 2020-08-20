package com.atguigu.test_deadlock;

import java.util.concurrent.TimeUnit;

class HoldLock implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己持有："+lockA+"\t尝试获得："+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t自己持有："+lockB+"\t尝试获得："+lockA);
            }
        }
    }
}

/**
 * @author zhudunfeng
 * @date 2020/8/20 22:54
 * 死锁是指两个或两个以上的进程在执行过程中，
 * 因为抢夺资源而造成的一种互相等待的现象，
 * 若无外力干预那么它们将无法推进下去
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";
        new Thread(new HoldLock(lockA, lockB),"ThreadAAA").start();
        new Thread(new HoldLock(lockB, lockA),"ThreadBBB").start();

        /**
         * 怎样定位死锁位置
         * linux   ps -ef|grep xxx   ls -l
         * windows下的java运行程序  也有类似ps的查看进程的命令，但是目前我们查看的只是java
         *         jps= java ps     jps -l 查询进程号
         *         jstack 进行号
         */
    }
}
