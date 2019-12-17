package com.atguigu.day04_threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        ExecutorService executorPool = Executors.newFixedThreadPool(3);

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
