package com.adun.test_jvm;

/**
 * @author zhudunfeng
 * @date 2020/8/26 22:54
 * 在java中，可作为GC Root的对象有：
 *
 * 1、虚拟机栈（栈帧中的本地变量表）中引用的对象；
 * 2、方法区中的类静态属性引用的对象；
 * 3、方法区中常量引用的对象；
 * 4、本地方法栈中JNI（即一般说的Native）中引用的对象
 *
 * 此类只是一些伪代码用于GCRoot的解释
 */
public class GCRootDemo {
    private byte[] byteArray=new byte[100*1024*1024];

    //static：2、方法区中的类静态属性引用的对象
    //private static GCRootDemo2 t2;
    //static final：3、方法区中的常量引用的对象
    //private static final GCRootDemo3 t3=new GCRootDemo3(8);

    public static void m1(){
        //1、 GCRootDemo t1:就是虚拟机栈中的引用指向堆中的对象
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

}
