package com.atguigu.day01_saletickets;

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 35; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 35; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 35; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();
    }
}
