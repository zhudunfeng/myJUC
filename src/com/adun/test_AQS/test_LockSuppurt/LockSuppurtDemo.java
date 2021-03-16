package com.adun.test_AQS.test_LockSuppurt;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zhudunfeng
 * @date 2021/3/16 15:26
 *
 *
 * LockSupport中的park()和unpark()的作用分别是阻塞线程和解除阻塞线程
 */
public class LockSuppurtDemo {
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "come in -----------");
            LockSupport.park();//被阻塞------等待放行，它需要获取许可证
            System.out.println(Thread.currentThread().getName() + "被唤醒---");
        }, "A");
        a.start();

        //等待几秒
        try {Thread.sleep(3000);} catch (InterruptedException e) { e.printStackTrace();}

        new Thread(()->{
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName()+"通知了-----");
        },"B").start();
    }
}
