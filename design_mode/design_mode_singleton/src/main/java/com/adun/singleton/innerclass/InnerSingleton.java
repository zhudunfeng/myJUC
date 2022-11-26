package com.adun.singleton.innerclass;

/**
 * 使用jvm静态内部类延时加载的特性
 *
 * 线程安全
 *
 * @author ADun
 * @date 2022/11/10 21:05
 */
public class InnerSingleton {

    private InnerSingleton() {
        System.out.println("这是 inner singleton");
    }

    private static class Singleton {
        private static final InnerSingleton instance= new InnerSingleton();
    }

    public static InnerSingleton getInstance(){
        return Singleton.instance;
    }
}
