package com.adun.entities;

/**
 * @author ADun
 * @date 2022/12/28 22:29
 */
public class SimplePerson implements Person {
    @Override
    public void run() {
        System.out.println("2只脚走路");
//        System.out.println("骑单车");
//        System.out.println("开车");
    }

    public static void main(String[] args) {
        new SimplePerson().run();
    }
}
