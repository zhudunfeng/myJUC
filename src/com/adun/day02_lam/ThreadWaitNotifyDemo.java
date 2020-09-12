package com.adun.day02_lam;

class Cake{
    private Integer cake=0;

    public synchronized void increment() throws InterruptedException {
        //判断
        while (cake!=0){
            this.wait();
        }
        //操作
        ++cake;
        System.out.println(Thread.currentThread().getName()+":"+cake);
        //通知
        notifyAll();
    }
    

    public synchronized void decrement() throws InterruptedException {
        while (cake==0){
            this.wait();
        }
        --cake;
        System.out.println(Thread.currentThread().getName()+":"+cake);
        notifyAll();
    }
}

/**
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零
 *
 * 1.线程操作资源类
 * 2.判断/干活/通知
 * 3.使用while（true）会将睡眠的线程重新进行判断
 *
 */
public class ThreadWaitNotifyDemo{
    public static void main(String[] args) {
        Cake eatCake = new Cake();
        new Thread(()->{
            try {
                Thread.sleep(200);
                for (int i = 1; i <=10; i++) {
                    eatCake.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                Thread.sleep(300);
                for (int i = 1; i <=10; i++) {
                    eatCake.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
       // TimeUnit.SECONDS.sleep(3);

        new Thread(()->{
            try {
                Thread.sleep(400);
                for (int i = 1; i <=10; i++) {
                    eatCake.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();

        new Thread(()->{
            try {
                Thread.sleep(500);
                for (int i = 1; i <=10; i++) {
                    eatCake.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();
    }
}
