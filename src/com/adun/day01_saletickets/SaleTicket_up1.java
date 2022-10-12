package com.adun.day01_saletickets;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket_up1{
    private  Integer ticket=100;
    //上锁
    Lock lock=new ReentrantLock();
    public  void sale(){
        lock.lock();
        try {
            if(ticket>0){
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName()+"卖出第"+(ticket--)+"张票,"+"还剩"+ticket+"张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 线程   操作  资源类
 */
public class SaleTicket_up1 {
    public static void main(String[] args) {
        Ticket_up1 ticket = new Ticket_up1();

        new Thread(() -> {
            for (int i = 1; i <= 120; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 120; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 120; i++) {
                ticket.sale();
            }
        }, "C").start();
    }

}
