package com.atguigu.day04_threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestNoSafeArrayListDemo {
    public static void main(String[] args) {
        //List<Object> list = new ArrayList<>();
        List<Object> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(6));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}

