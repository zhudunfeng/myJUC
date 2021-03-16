package com.adun.test_oom;

/**
 * @author zhudunfeng
 * @date 2021/3/10 10:28
 * 高并发请求服务器时，经常出现如下异常：Java.lang.OutOfMemeoryError:unable to create new native thread
 * 准确的讲native thread异常与对应的平台有关
 *
 * 导致原因：
 * 1、你的应用创建了太多线程，一个应用进程创建多个线程，超过系统承载极限
 * 2、你的服务器并不允许你的应用程序创建这么多线程，linux系统默认允许单个进程可以创建的线程数是1024个，
 * 你的应用创建超过这个数量，就会报Java.lang.OutOfMemeoryError:unable to create new native thread
 *
 * 解决办法：
 * 1、想办法降低你应用程序创建线程的数量，分析应用是否真的需要创建这么多线程，如果不是，改代码，将线程数降到最低
 * 2、对于有的应用，确实需要创建很多的线程，远超linux系统默认1024个线程的限制，可以通过修改linux服务器配置，扩大linux默认限制
 *
 * 系统默认情况
 * root: unlimit
 * 普通用户：1024
 */
public class NativeThreadMemoryDemo {
    public static void main(String[] args) {
        for (int i = 1; ; i++) {
            System.out.println("******************i="+i);
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }
}
