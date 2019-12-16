package com.atguigu.day03_printThree;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {

    private volatile Map<String, Object> map = new HashMap();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void write(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写===="+key);
            //暂停一会线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t 写完了===="+key);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Object read(String key) {
        rwLock.readLock().lock();
        try {
            Object result=null;
            System.out.println(Thread.currentThread().getName()+"\t 正在读~~~~"+key);
            //暂停一会线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
            result=map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读完了"+result);
            return result;
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

/**
 * 多个线程同时读一个资源没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源，就不应该在有其他线程可以对该资源进行独活写的操作
 * 小总结：
 *      读-读能共存
 *      读-写不能共存
 *      写-写不能共存
 *  演示：
 *  1.不加锁，乱写error，并发读可以
 *  2.加lock 写OK，但并发读下降
 *  3.加ReentrantReadWriteLock,写唯一，读并发高性能
 * 读写锁
 *
 * 共享读
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <=10; i++) {
            final int TempI=i;
            new Thread(() -> {
                myCache.write(""+TempI,""+TempI);
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <=10; i++) {
            final int TempI=i;
            new Thread(() -> {
                myCache.read(""+TempI);
            }, String.valueOf(i)).start();
        }
    }
}
