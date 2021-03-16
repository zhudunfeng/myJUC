package com.adun.test_jvm;

/**
 * @author zhudunfeng
 * @date 2020/11/24 14:54
 *
 * 强引用案例
 *
 *
 * 当内存不足，JVM开始垃圾回收，对于强引用的对象，就算是出现OOM也不会对该对象进行回收，死都不收。
 *
 * 强引用是我们最常见的普通对象引用，只要还有强引用指向一个对象，就能表明对象还“活着”，垃圾收集器不会碰这种对象。
 * 在Java中最常见的就是强引用，把一个对象赋给一个引用变量，这个引用变量就是一个强引用。
 * 当一个对象被强引用变量引用时，它处于可达状态，它是不可能被垃圾回收机制回收的，即使该对象以后永远不会被用到JVM也不会进行回收。
 * 因此，强引用是造成Java内存泄漏的主要原因之一。
 *
 * 对于一个普通对象，如果没有其他的引用关系，只要超过了引用的作用域或者显式地将相应（强）引用赋值为null，
 * 一般认为就是可以被垃圾收集的了（当然具体回收时机还是要看垃圾收集策略）
 *
 *
 *
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();//这样定义的默认就是强引用
        Object obj2 = obj1;//obj2引用赋值
        obj1=null;//置空
        System.gc();
        System.out.println(obj2);
    }
}
