package com.adun.test_concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zhu Dunfeng
 * @date 2022/4/27 11:14
 */
public class ConcurrentHashMapTest {

    public static final ThreadLocal<Integer> threadLocal=new ThreadLocal();

    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("aa", 1);
//        new Thread(() -> {
            threadLocal.set(234);
//            Integer num1 = threadLocal.get();
//            System.out.println(num1);
//            threadLocal.remove();
            Integer num2 = threadLocal.get();
            System.out.println(num2);
//        },"Thread1").start();


    }
}
