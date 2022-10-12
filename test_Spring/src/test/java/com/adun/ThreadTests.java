package com.adun;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Zhu Dunfeng
 * @date 2022/4/13 16:13
 */
@SpringBootTest
public class ThreadTests {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Test
    public void testThread1(){
        //完成时返回结果值，如果异常完成，则抛出(未检查的)异常。
        CompletableFuture.runAsync(() -> {
            int i = 10 / 0;
        },threadPoolExecutor).join();

    }

    @Test
    public void testThread2(){
        //如果需要，等待该future完成，然后返回其结果。
        try {
            CompletableFuture.runAsync(() -> {
//                int i = 10 / 0;
            },threadPoolExecutor).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testThread3(){
        //如果需要，等待该future完成，然后返回其结果。
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
             try {Thread.sleep(3000);} catch (InterruptedException e) { e.printStackTrace();}
            int i = 10 / 0;
//            System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
        }, threadPoolExecutor);

        //主程序不等待
//        CompletableFuture.allOf(future).whenComplete((aVoid, throwable) -> {
//            throwable.printStackTrace();
//        });

        //主程序不等待
//        future.exceptionally(e->{
//            e.printStackTrace();
//            return null;
//        });

        //如果此调用导致CompletableFuture转换为完成状态，则为true，否则为false
//        boolean complete = future.complete(null);
//        System.out.println(complete);

        //主程序不等待
//        future.handleAsync((a,throwable)->{
//            throwable.printStackTrace();
//            return null;
//        },threadPoolExecutor);

        //主线程等待运行结果，底层使用线程插队
//        future.join();


        //主线程不等待结果
//        CompletableFuture.allOf(future).exceptionally(e->{
////            e.setStackTrace(new StackTraceElement[]{
////                    new StackTraceElement(this.getClass().getName(),"testThread3","ThreadTests.java",79)});
////            StackTraceElement[] stackTrace = e.getStackTrace();
////            System.out.println(Arrays.toString(stackTrace));
//            e.printStackTrace();
//            return null;
//        });

        //主线程不等待结果
        CompletableFuture.allOf(future).exceptionally(e->{
            e.printStackTrace();
            return null;
        });
        try {Thread.sleep(4000);} catch (InterruptedException e) { e.printStackTrace();}

    }


    @Test
    public void testThread4(){

        threadPoolExecutor.execute(()->{
            int i=10/0;
            System.out.println("aaaa");
        });

    }

    @Test
    public void testThread5() throws ExecutionException, InterruptedException {
        Future<String> f = threadPoolExecutor.submit(() -> {
            int i=10/0;
            System.out.println("aaaa");
            return "aaa";
        });
        String s = f.get();
        System.out.println("s = " + s);
    }
}
