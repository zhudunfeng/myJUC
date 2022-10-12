package com.adun.log;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ADun
 * @date 2022/8/5 22:49
 */
@Component
public class LogContainer {

    //定义一个队列
    private static LinkedBlockingQueue logs = new LinkedBlockingQueue(1000);

    public LogContainer() {
        logPrint();
    }

    public static void log(Object value){
        logs.offer(value);
    }


    public void logPrint(){
        new LogThread().start();
    }

    class LogThread extends Thread{
        @Override
        public void run() {
            while (true){
                if(logs.size()>0){
                     try {Thread.sleep(3000);} catch (InterruptedException e) { e.printStackTrace();}
                    //Object poll = logs.poll();
                    ArrayList arrayList = new ArrayList();
                     //批量取出队列中的日志
                    logs.drainTo(arrayList, 100);
                    arrayList.stream().forEach(System.out::println);
                    System.out.println("1\n\n");
                }else{
                    try {
                        //没有进行阻塞
                        Object take = logs.take();
                        System.out.println(take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
