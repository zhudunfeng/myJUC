package com.adun.test_AQS.test_LockSuppurt;

import java.util.concurrent.TimeUnit;

/**
 * @author zhudunfeng
 * @date 2021/3/16 16:24
 *
 * 要求：t1线程等待3秒种，3秒种后t2线程唤醒t1线程继续工作：
 *
 * 以下是异常情况：
 * 2 wait方法和notify方法，两个都去掉同步代码块后看运行效果
 *  2.1 异常情况
 *  Exception in thread "A"  java.lang.IllegalMonitorStateException at java.lang.Object.wait(Native Method)
 *  Exception in thread "B" B通知====== java.lang.IllegalMonitorStateException at java.lang.Object.notify(Native Method)
 *  2.2结论
 *  Object类中的wait、notify、notifyAll用于线程等待和唤醒的方法，都必须在synchronized内部执行（必须用到关键字synchronized）
 *
 *
 *  3 将notify放在wait方法前先执行，t1先notify了，3秒种后t2线程再执行wait方法
 *  3.1程序一直无法结束
 *  3.2结论
 *  先wait后notify、notifyAll方法，等待中的线程才会被唤醒，否则无法唤醒
 */
public class WaitNotifyDemo {
    static Object object1=new Object();//同一把锁，类似资源类

    public static void main(String[] args) {//main方法，主程序一切程序的入口
        synchronizedWaitNotify();
    }

    private static void synchronizedWaitNotify() {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3L);} catch (InterruptedException e) { e.printStackTrace();}
            synchronized (object1){
                System.out.println(Thread.currentThread().getName()+"comn in------------");
                try {
                    object1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"被唤醒=====");
            }
        },"A").start();
        new Thread(()->{
            synchronized (object1){
                System.out.println(Thread.currentThread().getName()+"通知======");
                object1.notify();
            }
        },"B").start();
    }

}
