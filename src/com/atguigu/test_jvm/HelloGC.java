package com.atguigu.test_jvm;

/**
 * @author zhudunfeng
 * @date 2020/8/29 22:13
 *
 * JVM参数调优：
 *  （1）标准参数
 *  （2）X参数（了解）：
 *          Xint:解释执行
 *          Xcomp:第一次使用就编译成本地代码
 *          Xmixed:混合模式，（先编译再执行）
 *  （3）XX参数
 *      （i）Boolean类型
 *      （ii）KV设值类型
 *
 *      常用的-XX参数
 *      -Xms JVM初始大小内存，默认为物理内存的1/64     等价于-XX:InitialHeapSize
 *      -Xmx JVM最大分配内存，默认为物理内存的1/4      等价于-XX:MaxHeapSize
 *      -Xss 设置单个线程栈的大小，一般默认为512k~1024k 这里是根据Jvm所在平台来确定【查看官网Java文档】 等价于-XX:ThreadStackSize
 *      -Xmn 设置年轻代大小【年轻代中【伊甸区：幸存0区：幸存1区】【8：1：1】】
 *      -XX:MetaspaceSize 设置元空间的大小
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {

        //返回Java的虚拟机中的内存信息
        long totalMemory = Runtime.getRuntime().totalMemory();
        //返回Java虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TotalMembery(Xms)=:"+totalMemory+"(字节)、"+(totalMemory/(double)1024/1024)+"MB");
        System.out.println("TotalMembery(Xmx)=:"+maxMemory+"(字节)、"+(maxMemory/(double)1024/1024)+"MB");

        System.out.println("*************hello java");

        //作死操作，new 一个对象超过最大内存
//        byte[] aByte = new byte[50*1024*1024];
//        Thread.sleep(Integer.MAX_VALUE);
    }
}
