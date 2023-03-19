package com.adun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author Zhu Dunfeng
 * @date 2022/3/2 21:17
 */
@Component
public class Test implements Runnable{


    public static final SynchronizedByKey synchronizedByKey=new SynchronizedByKey();

    public static void main(String[] args) {
        for (int i = 0; i < 20000; i++) {
            new Thread(new Test()).start();
        }
    }



    public void service(){
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        synchronizedByKey.exec("001", ()->{
            System.out.println("开始");
            service();
            System.out.println("结束");
        });
    }
}
