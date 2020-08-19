package com.atguigu.testThreadPool;



import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * @author zhudunfeng
 * @date 2020/8/19 22:14
 * 第四种获取/Java使用多线程的方式,线程池
 * 在日常的工作中，建议使用自定义线程池，因为Executors.newCachedThreadPool();获取到的线程池默认的阻塞队列大小是正整数的Integer.MAX_VALUE
 * 拒绝策略：AbortPolicy【抛弃，会抛出异常RejectedExecutionException】（默认），
 * CallerRunsPolicy【调用者执行，谁调用线程池中的线程，多出的线程调用线程进行执行】，
 * DiscardPolicy【直接抛弃多出的任务】，
 * DiscardOldestPolicy【抛弃阻塞队列中最久的任务】
 *
 *
 * 怎样配置线程池合理线程数
 * cpu密集型
 *  cpu密集型任务配置尽可能少的线程数量：一般公式：cpu核数+1个线程的线程池
 * IO密集型
 * （1）由于IO密集型任务线程并不是一直在执行任务，则应配置尽可能多的线程，如cpu核数*2
 * （2）IO密集型时，大部分线程都阻塞，故需要多配置线程数：
 *  参考公式：CPU核数/1-阻塞系数       阻塞系数在0.8~0.9之间
 *  比如8核CPU： 8/（1-0.9）=80个线程数
 *
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());//cpu核数

        Logger logger = Logger.getLogger(MyThreadPoolDemo.class.getName());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        } catch (Exception e) {
            logger.log(Level.WARNING,"超过最大线程" );
            e.printStackTrace();
        }
    }

}
