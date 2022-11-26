package com.adun.singleton.enums;

/**
 * @author ADun
 * @date 2022/11/10 21:15
 */
public enum  EnumSingleton {

    //定义一个枚举,代表了singleton的一个实例
    INSTANCE;

    private EnumSingleton(){
        System.out.println("这是enum singleton");
    }

    public void  anyMethond(){
        System.out.println("do any thing");
    }

}
