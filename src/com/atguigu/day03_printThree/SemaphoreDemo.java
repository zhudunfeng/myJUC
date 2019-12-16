package com.atguigu.day03_printThree;

import javax.sound.midi.Soundbank;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 在信号量上我们定义的两种操作：
 * acquire() （获取）当一个线程调用acquire() 时，它要么通过成功获取信号量（信号量减1）
 * 要么一直等待下去，直到有线程释放信号量，或超时
 * <p>
 * release() （释放）实际上会将信号量的值加1，然后唤醒等待的线程
 * <p>
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个是并发线程数控制
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位

        for (int i = 1; i <= 6; i++) {//模拟6部汽车占车位
            new Thread(() -> {
                boolean flag=false;
                try {
                    semaphore.acquire();
                    flag=true;
                    System.out.println(Thread.currentThread().getName()+"\t 抢到停车位");
                    //暂停几秒钟线程
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t 离开停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if(flag=true){
                        semaphore.release();
                    }
                }
            }, String.valueOf(i)).start();
        }
    }
}
