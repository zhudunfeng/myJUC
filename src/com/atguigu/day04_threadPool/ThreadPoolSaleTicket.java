package com.atguigu.day04_threadPool;

import java.util.concurrent.*;

class Ticket {
    private Integer ticket = 30;

    //上锁
    public synchronized void sale() {
        if (ticket > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖出第" + (ticket--) + "张票," + "还剩" + ticket + "张票");
        }
    }
}

/**
 * 线程  操作  资源
 */
public class ThreadPoolSaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

       // ExecutorService executorPool = Executors.newFixedThreadPool(3);
        //不要使用Executors工具类创建线程池，因为它使用的是Integer.MAX_VALUE，会造成资源耗尽
        ExecutorService executorPool=new ThreadPoolExecutor(
                3,
                15,
                6L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue(15),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
                );
        try {
            for (int i = 1; i <= 30; i++){
                executorPool.execute(() -> {
                        ticket.sale();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorPool.shutdown();
        }


    }
}
