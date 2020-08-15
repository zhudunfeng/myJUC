package com.atguigu.test_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 第一种
 */
class MyThread extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("继承Thread");
    }
}
/**
 * 第二种
 */
class MyThread1 implements Runnable{
    @Override
    public void run() {
        System.out.println("实现Runnable接口");
    }
}

/**
 * 第三种
 */
class MyThread2 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("Thread:"+Thread.currentThread().getName()+"..............come in callable");
        return 1024;
    }
}
/**
 * 第四种 ：线程池
 */

/**
 * @author zhudunfeng
 * @date 2020/8/15 22:54
 * 多线程中，第三种获取多线程的方式
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* MyThread myThread = new MyThread();
        myThread.start();*/

        /*MyThread1 myThread1 = new MyThread1();
        Thread thread = new Thread(myThread1);
        thread.start();*/

        FutureTask futureTask = new FutureTask<>(new MyThread2());
        new Thread(futureTask,"AA").start();
        //此时不会进入BB线程，会复用AA线程的结果
        new Thread(futureTask,"BB").start();
        int result01=100;

      /*  while (!futureTask.isDone()){

        }*/
        //建议放在最后，要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致阻塞直到计算完成
        int result02 = (int) futureTask.get();

        System.out.println("callable return value:"+(result01+result02));

    }

}
