package com.atguigu.test_jvm;

import java.lang.ref.WeakReference;

/**
 * @author zhudunfeng
 * @date 2020/9/8 23:05
 * 弱引用案例
 * 无论内存是否充足，gc直接回收
 *
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);

        //gc前
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1=null;
        System.gc();
        //gc后
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
