package com.adun.day03_printThree;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData01 {
    private int flag = 1;
    private int threadStat=0;
    //锁
    private Lock lock = new ReentrantLock();
    //三把钥匙
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print() throws InterruptedException {
        String threadName=Thread.currentThread().getName();
        if("A".equals(threadName)){
            threadStat=1;
        }else if("B".equals(threadName)){
            threadStat=2;
        }else if("C".equals(threadName)){
            threadStat=3;
        }
        lock.lock();
        try {
            //判断
            while(flag!=threadStat){//2 3
                if(threadStat==1){
                    c1.await();
                }
                if(threadStat==2){
                    c2.await();
                }
                if(threadStat==3){
                    c3.await();
                }
            }

            //干活
            for (int i = 1; i <=5*flag ; i++) {
                System.out.println(Thread.currentThread().getName()+"/"+i);
            }
            //通知
            if(threadStat==1){
                flag=2;
                c2.signal();
            }
            if(threadStat==2){
                flag=3;
                c3.signal();
            }
            if(threadStat==3){
                flag=1;
                c1.signal();
            }
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
public class ThreeInformation01 {
    public static void main(String[] args) {
        ShareData01 shareData = new ShareData01();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    shareData.print();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    shareData.print();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    shareData.print();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

    }

}
