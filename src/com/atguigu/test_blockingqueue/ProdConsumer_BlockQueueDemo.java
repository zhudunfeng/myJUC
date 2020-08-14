package com.atguigu.test_blockingqueue;

import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG=true;//默认开启，进行生产+消费
    private AtomicInteger atomicInteger=new AtomicInteger();

    BlockingQueue<String> blockingQueue=null;
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue=blockingQueue;
        System.out.println(blockingQueue.getClass().getName());//打印日志，七种阻塞队列，确定是哪种传进来的
    }

    public void myProd() throws Exception{
        String data=null;
        boolean retValue;
        while (FLAG){
            data=atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t"+"插入队列"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t"+"插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t大老板叫停了，标识FLAG=false，生产动作结束\n\n\n");
    }

    public void myConsumer() throws Exception{
        String result=null;
        //生产消费同生共死
        while (FLAG){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null==result||result.equalsIgnoreCase("")){
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t超过2秒钟没有取到值，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费队列"+result+"成功");
        }
    }

    public void stop() throws Exception{
        this.FLAG=false;
    }
}
/**
 * @author zhudunfeng
 * @date 2020/8/13 23:49
 *
 *  volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        //暂停一会线程
        try {TimeUnit.SECONDS.sleep(10);} catch (InterruptedException e) { e.printStackTrace();}
        System.out.println(Thread.currentThread().getName()+"\t5秒钟时间到，大老板main线程叫停，活动结束\n\n\n");
        try {
            myResource.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
