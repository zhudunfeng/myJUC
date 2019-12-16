package com.atguigu.day01_saletickets;

class MyThread implements Runnable {

    private static Integer ticket = 35;

    @Override
    public  void run() {
        while (true) {
            synchronized (this.getClass()) {
                if (ticket < 1) {
                    System.out.println("已经卖没了");
                    break;
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "买了第" + (ticket--) + "张票，剩余票数：" + ticket);
                }
            }

        }
    }
}
