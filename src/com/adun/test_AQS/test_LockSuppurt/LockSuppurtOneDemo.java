package com.adun.test_AQS.test_LockSuppurt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhudunfeng
 * @date 2021/3/16 15:26
 *
 *
 * LockSupport中的park()和unpark()的作用分别是阻塞线程和解除阻塞线程
 *
 * 因为凭证的数量最多为1，连续调用两次unpark和调用一次unpark效果一样，permit从0变1不会进行累加
 * 只会增加一个凭证； 而调用两次park却需要消费两个凭证，凭证不够，不能放行，第二次调用的park会进行阻塞
 *
 *
 */
public class LockSuppurtOneDemo {
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            //等待几秒 当前线程
            try {TimeUnit.SECONDS.sleep(3L);} catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "come in -----------"+System.currentTimeMillis());
            LockSupport.park();//被阻塞------等待放行，它需要获取许可证
//            LockSupport.park();//被阻塞------等待放行，它需要获取许可证
            System.out.println(Thread.currentThread().getName() + "被唤醒---"+System.currentTimeMillis());
        }, "A");
        a.start();

        //先通知，然后阻塞
        new Thread(()->{
            LockSupport.unpark(a);
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName()+"通知了-----");
        },"B").start();


    }
}
