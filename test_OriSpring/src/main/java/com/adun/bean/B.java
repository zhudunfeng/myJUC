package com.adun.bean;

/**
 * @author zhudunfeng
 * @date 2021/6/7 16:42
 */
public class B {
    private A a;

    public B() {
        System.out.println("--B create success");
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
