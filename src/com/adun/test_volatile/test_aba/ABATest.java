package com.adun.test_volatile.test_aba;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Auther ADun
 * @Date 2020/7/15
 * ABA[狸猫换太子]
 * 理解ABA问题的产生与解决方法
 *
 */
public class ABATest {
    private static AtomicReference<Integer> atomicReference=new AtomicReference(100);
    private static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        /**
         * ABA问题的产生
         */
        new Thread(()->{
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        },"t1").start();

        new Thread(()->{
            //暂停一秒钟t2线程，保证上面的t1线程完成一次ABA操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2020)+"\t"+atomicReference.get());
        },"t2").start();


        /**
         * ABA问题解决
         */
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();//得到第一次版本号
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号"+stamp);
            //暂停1秒t3线程，保证t4线程也能够取到第一次版本号，两个线程的起始版本一致
             try {Thread.sleep(1000);} catch (InterruptedException e) { e.printStackTrace();}
             atomicStampedReference.compareAndSet(100, 101,
                     atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第2次版本号"+atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101, 100,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第3次版本号"+atomicStampedReference.getStamp());

        },"t3").start();
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();//得到第一次版本号
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号"+stamp);
            //暂停3秒t4线程，保证t3线程也能够完成一次ABA操作
            try {Thread.sleep(3000);} catch (InterruptedException e) { e.printStackTrace();}
            boolean result = atomicStampedReference.compareAndSet(100, 2020,
                    stamp, stamp + 1);

            System.out.println(Thread.currentThread().getName()+"成功与否"+result+"\t当前版本号"+atomicStampedReference.getStamp());
        },"t4").start();






    }






}
