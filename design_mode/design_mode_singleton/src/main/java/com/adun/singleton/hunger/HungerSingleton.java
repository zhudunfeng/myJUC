package com.adun.singleton.hunger;

/**
 * 单例模式  饿汉模式
 *
 * @author ADun
 * @date 2022/11/10 20:56
 */
public class HungerSingleton {

    private static final HungerSingleton HUNGERSINGLETON=new HungerSingleton();

    private HungerSingleton() {
        System.out.println("我是hunger");
    }

    public static HungerSingleton getHungerSingleton() {
        return HUNGERSINGLETON;
    }
}
