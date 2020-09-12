package com.adun.test_blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhudunfeng
 * @date 2020/8/11 20:25
 *
 * 题目：synchronized和Lock有什么区别？用新的Lock有什么好处？你举例说说
 * 1、原始构成
 *  synchronized是关键字属于JVM层面，
 *    monitorenter（底层是通过monitor对象来完成，
 *    其实wait/noify等方法也依赖于monitor对象只有在同步代码块或方法中才能调用wait/notify等方法）
 *    monitorexit
 *  Lock是具体类（java.util.concurrent.locks.lock）是api层面的锁
 *
 * 2、使用方法
 *  synchronized不需要用户手动释放锁，当synchronized代码执行完成后系统会自动让线程释放对锁的占用
 *  ReentrantLock则需要用户手动去释放锁，若没有主动释放锁，就有可能导致出现死锁现象。
 *  需要lock()和unlock()方法配合try/finally语句块来完成。
 *
 * 3、等待是否可中断
 *  synchronized不可中断，除非抛出异常或者正常运行完成
 *  ReentrantLock可中断，1.设置超时方法trylock(Long timeout,TimeUnit unit)
 *                      2.lockInterruptibly()放代码块中，调用interrupt()方法可中断
 * 4、加锁是否公平
 *  synchronized非公平锁
 *  ReentrantLock两者都可以，默认公平锁，构造方法可以传入boolean值，true为公平锁，false为非公平锁
 *
 * 5 锁绑定多个条件Condition
 *  synchronized没有
 *  ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized，要么随机唤醒一个线程，
 *  要么唤醒全部的线程
 * =====================================================
 * =====================================================
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 * ...
 * ....
 *
 * 精准通知（while判断防止虚假唤醒）
 */

//资源类【共享资源】
class ShareResource{
    private int number=1;
    private Lock lock = new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();

    //打印5次
    public void print5() {
        //上锁
        lock.lock();
        try {
            //判断
            while (number!=1){
                c1.await();
            }
            //干活
            printCommon(5);
            number=2;
            //通知
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //打印5次
    public void print10() {
        //上锁
        lock.lock();
        try {
            //判断
            while (number!=2){
                c2.await();
            }
            //干活
            printCommon(10);
            number=3;
            //通知
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //打印5次
    public void print15() {
        //上锁
        lock.lock();
        try {
            //判断
            while (number!=3){
                c3.await();
            }
            //干活
            printCommon(15);
            number=1;
            //通知
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private void printCommon(int num){
        for (int i = 1; i <= num ; i++) {
            System.out.println(Thread.currentThread().getName()+"\t"+i);
        }
    }
}

public class SyncAndReetrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        },"C").start();

    }

}
