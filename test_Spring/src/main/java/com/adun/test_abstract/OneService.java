package com.adun.test_abstract;

/**
 * @author zhudunfeng
 * @date 2021/6/10 13:56
 */
public interface OneService extends AIn, BIn {

    public static final String a = "哈哈哈";

    public abstract void print();

    public default void printOne() {
        System.out.println(a);
    }
}
