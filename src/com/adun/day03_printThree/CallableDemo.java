package com.adun.day03_printThree;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//定义线程
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}


/**
 * 多线程中，第三种获得线程的方式
 *
 * Callable 于 Runnable获取线程的对比
 * （1）是否有返回值
 * （2）是否抛出异常
 * （3）落地方法一个是run() 一个是call()
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyThread());
        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());
    }
}
