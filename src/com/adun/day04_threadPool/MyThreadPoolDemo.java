package com.adun.day04_threadPool;


import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args)
    {
        /*ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();//一池1线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();//一池N线程*/

        //ExecutorService threadPool = Executors.newFixedThreadPool(5);

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());


        // 一个银行已经new好了5个受理窗口，有5个工作人员。
        try
        {
            //一池5个工作人员(银行受理窗口),模拟20个客户(request)来银行实体店办理业务。
            for (int i = 1; i <=12; i++)
            {
                final int tempI = i;
                threadPool.execute(() -> {System.out.println(Thread.currentThread().getName()+"\t 受理业务"+"\t 客户号："+tempI);});
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }



    }
}
