package com.atguigu.day04_threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列核心方法练习
 * 抛出异常
 * add()
 * remove()
 * element()
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        //第一组，抛出异常组
        //添加元素
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("d"));
        //取出元素
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());

        //取出队头元素
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.element());



        //第二组：true false组
        //添加元素
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d",1, TimeUnit.SECONDS));

        TimeUnit.SECONDS.sleep(2);
        //取出元素
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
        //System.out.println(blockingQueue.poll());

        //取出队首元素
        //System.out.println(blockingQueue.peek());

    }
}
