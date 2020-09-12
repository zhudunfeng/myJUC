package com.adun.test_jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author zhudunfeng
 * @date 2020/9/7 21:43
 * 测试引用队列
 *
 * 获得引用队列特点，当前引用被gc回收前会自动放入引用队列
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) {

        Object o1 = new Object();
        //新建引用队列
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        //新建虚引用
        WeakReference<Object> weakReference = new WeakReference<Object>(o1,referenceQueue);
        //打印gc前的结果
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=====================================");

        //设置o1为null
        o1=null;
        //手动调用系统gc
        System.gc();
        //阻塞一段时间，保证gc完成
         try {Thread.sleep(500);} catch (InterruptedException e) { e.printStackTrace();}
         //打印gc结果
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

    }
}
