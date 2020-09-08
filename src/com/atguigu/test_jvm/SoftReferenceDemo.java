package com.atguigu.test_jvm;

import java.lang.ref.SoftReference;

/**
 * @author zhudunfeng
 * @date 2020/9/8 22:28
 *
 * 软引用的特点：内存充足，不会进行回收；内存不足，会直接进行回收
 *
 *  案例：两种情况，内存充足，内存不足
 *
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
        //softMemoryEnough();

        //System.out.println("=========================");

        softMemoryUnenough();
    }

    //内存充足
    public static void  softMemoryEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);

        //gc前
        System.out.println(o1);
        System.out.println(softReference.get());

        //置空
        o1=null;
        //手动gc
        System.gc();

        //gc后
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    //内存不足

    /**
     * JVM配置，故意产生大对象并配置小的内存，让它内存不够用，导致OOM，看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void  softMemoryUnenough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);

        //gc前
        System.out.println(o1);
        System.out.println(softReference.get());

        //置空
        o1=null;
        //手动gc
        System.gc();

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //gc后
            System.out.println(o1);
            System.out.println(softReference.get());
        }

    }

}
