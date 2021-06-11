package com.adun.bean;

/**
 * @author zhudunfeng
 * @date 2021/6/7 16:42
 */
public class A {
    private B b;

    public A() {
        System.out.println("--A create success");
    }


    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
