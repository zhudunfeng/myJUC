package com.atguigu.day03_printThree;

public class CurrrentDown {
    public static void main(String[] args) {
        
        for (int i = 1; i <=10 ; i++) {
                    new Thread(()->{

                    },String.valueOf(i)).start();
                }
    }
}
