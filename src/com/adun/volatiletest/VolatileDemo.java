package com.adun.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther ADun
 * @Date 2020/7/18 22:55
 * volatile保证原子性的代码案例
 * 解决案例
 */
class MyData{
  volatile int number=0;
  public void addT060(){
      this.number=60;
  }

  //存在问题的代码
  //请注意，此时的number前面时加了volatile关键字修饰的，volatile不保证原子性
    public void addPlusPlus(){
      number++;
    }

    //解决问题的代码
    AtomicInteger atomicInteger=new AtomicInteger();//原子整数，初始化为零，源码
    public void addMyPlusPlus(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1、验证volatile的可见性
 *  1.1假如int number=0； number变量之前根本没有添加volatile关键字修饰，没有可见性
 *  1.2添加volatile，可以解决可见性问题
 *
 * 2、 验证volatile不保证原子性
 *  2.1原子性指的是什么意思？
 *      不可分割，完整性，也即某个线程正在做某个具体业务，中间不可以被加塞或被分割，需要整体完整
 *      要么同时成功，要么同时失败
 *   2.2volatile不保证原子性的案例演示
 *
 *   2.3why
 *
 *   2.4如何解决原子性？
 *    （1）加synchronization关键字，较重
 *    （2）使用java.utils.currment下的AtomicInteger
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <=1000 ; j++) {
                    myData.addPlusPlus();
                    myData.addMyPlusPlus();
                }
            },String.valueOf(i)).start();
        }

        //需要等待上面的20个线程都全部计算完成后，再用main线程取得最终的结果值，看是多少
        while (Thread.activeCount()>2){//如果用两个线程存活，main线程暂停
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"\t finally number value:"+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t finally AtomicInteger value:"+myData.atomicInteger);
    }
}
