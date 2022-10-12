package com.adun.day02_lock.lock8;

import java.util.concurrent.TimeUnit;

class Phone2 {
    public synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------------sendEmail");
    }

    public synchronized void sendSms() {
        System.out.println("------------sendSMS");
    }

    public void hello() {
        System.out.println("hello");
    }
}

/**
 * 题目：
 * 1.标准访问，请问是先打印邮件还是短信  Email
 * 2.email方法新增暂停4秒钟，请问是先打印邮件还是短信    Email
 * 3.新增普通的hello方法，请问先打印邮件还是hello    hello
 * 4.两部手机，请问先打印邮件还是短信   SMS
 * 5.两个静态同步方法，1部手机，请问先打印邮件还是短信     Email
 * 6.两个静态同步方法，2部手机，请问先打印邮件还是短信     Email
 * 7.一个普通同步方法，一个静态同步方法，1部手机，请问先打印邮件还是短信     SMS
 * 8.一个普通同步方法，一个静态同步方法，2部手机，请问先打印邮件还是短信     SMS
 * <p>
 * lock1、2
 * 一个对象里面如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中的一个synchronized方法，
 * 其他的线程都只能等待，换句话说，某一时刻内，只能有唯一一个线程去访问这些synchronized方法，
 * 锁的是当前对象的this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
 * <p>
 * lock3、4
 * 加个普通的方法后和同步锁无关
 * 换成两个对象后，不是同一把锁了，情况立刻变化
 * <p>
 * lock5、6
 * 都换成静态同步方法后，情况又变化（静态锁的是Class类对象）
 * 若是普通同步方法，new   this，具体的一部一部手机，所有的普通同步方法用的都是同一把锁----示例对象本身
 * 若是静态同步方法，static   class，唯一的一个模板
 * synchronized是实现同步的基础：Java中的每一个对象都可以作为锁
 * 具体表现为一下3种形式。
 * 对于普通同步方法，锁是当前实例对象。它等同于  对于同步方法块，锁是synchronized括号里的配置的对象。
 * 对于静态同步方法，锁是当前类的Class对象本身
 * <p>
 * lock7、8
 * 当一个线程试图访问同步代码块时它首先必须得到锁，退出或抛出异常时必须释放锁
 * <p>
 * 所有的同步方法用的都是同一把锁----实例对象本身，就是new出来的具体实例对象本身
 * 也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他普通同步方法必须等待获取锁的方法释放锁后才能获得锁
 * 可是别的实例对象的普通同步方法因为跟该实例对象的普通同步方法用到是不同锁，所以不用等待该实例对象已获取锁的普通
 * 同步方法释放锁就可以获取它们自己的锁
 * <p>
 * 所有的静态同步方法用的也是同一把锁----类对象本身，就是我们说过的唯一模板Class
 * 具体实例对象this和唯一模板Class，这两把锁是两个不同的对象，所有静态同步方法与普通同步方法之间是不会有竞态条件的，
 * 但是一旦一个静态同方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁。
 */
public class Lock2 {
    /**
     * lock2 是lock1的变形
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Phone2 phone = new Phone2();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
//        Thread.sleep(300);
        new Thread(() -> {
            //phone.sendSms();
            phone.sendSms();
            // phone.hello();
        }, "B").start();

    }
}
