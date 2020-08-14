package com.atguigu.test_blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 队列中始终只有一个值，放完再取
 *
 * SynchronousQueue没有容量
 *
 * 与其他BlcokingQueue不同,SynchronousQueue是一个不存储元素的BlcokingQueue
 *
 * 每个put操作必须要等待一个take操作,否则不能继续添加元素,反之亦然.
 * @author zhudunfeng
 * @date 2020/8/10 21:03
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\tput 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName()+"\tput 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName()+"\tput 3");
                blockingQueue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(()->{
            try {
                //暂停一会线程
                try {
                    TimeUnit.SECONDS.sleep(5);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
                try {
                    TimeUnit.SECONDS.sleep(5);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
                try {
                    TimeUnit.SECONDS.sleep(5);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
