package com.atguigu.day03_printThree;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {

    private Map<String, String> map = new HashMap();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void write(String key, String value) {
        rwl.writeLock();
        System.out.println("写数据开始");
        map.put(key, value);
        System.out.println("写数据结束");
    }

    public void read(String key) {
        rwl.readLock();
        System.out.println("读数据开始");
        System.out.println("读数据结束"+map.get(key));
    }
}

/**
 * 读写锁
 *
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
