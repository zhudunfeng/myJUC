package com.adun.controller;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Zhu Dunfeng
 * @date 2022/3/2 19:15
 */
@Component
public class SynchronizedByKey {

    //翻车1
//    Map<String,Object> mutexCache = new ConcurrentHashMap();

//    public void exec(String key,Runnable statement){
//
//     Object mutexKey = mutexCache.computeIfAbsent(key, k -> new Object());
//
//        synchronized (mutexKey){
//            try {
//                statement.run();
//            } finally {
//                mutexCache.remove(key);
//            }
//        }
//
//    }

    /**
     * 翻车2
     */
//    Map<String, ReentrantLock> mutexCache = new ConcurrentHashMap();
//
//    public void exec(String key, Runnable statement) {
//
//        /**
//         * 锁获取的三种情况：
//         * (1)获取到自己创建的
//         * (2)获取到别人创建的（正常的、有排队线程的）
//         * (3)拿到一把刚刚被remove的锁
//         */
//        ReentrantLock mutex4Key = mutex4Key = mutexCache.computeIfAbsent(key, k -> new ReentrantLock());;
//
//        mutex4Key.lock();
//        try {
//            statement.run();
//        } finally {
//            if (mutex4Key.getQueueLength() == 0) {
//                mutexCache.remove(key);
//            }
//            mutex4Key.unlock();
//        }
//    }



    Map<String, ReentrantLock> mutexCache = new ConcurrentHashMap();

    public void exec(String key, Runnable statement) {

        /**
         * 锁获取的三种情况：
         * (1)获取到自己创建的
         * (2)获取到别人创建的（正常的、有排队线程的）
         * (3)拿到一把刚刚被remove的锁
         */
        ReentrantLock mutex4Key = null;
        ReentrantLock mutexInCache = null;

        do {
            if(mutex4Key!=null){
                mutex4Key.unlock();
            }
            mutex4Key = mutexCache.computeIfAbsent(key, k -> new ReentrantLock());
            mutex4Key.lock();

            mutexInCache = mutexCache.get(key);
            /**
             * 双重检查
             *  1.mutexInCache==null 获取到的锁被移除掉了
             *  2.mutex4Key!=mutexInCache 和缓存中不一致了  lock后锁被删除，新进来的线程又创建了新锁
             */
        } while (mutexInCache == null || mutex4Key != mutexInCache);

        try {
            statement.run();
        } finally {
            if (mutex4Key.getQueueLength() == 0) {
                mutexCache.remove(key);
            }
            mutex4Key.unlock();
        }
    }


}
