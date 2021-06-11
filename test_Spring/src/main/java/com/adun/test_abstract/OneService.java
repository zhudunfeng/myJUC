package com.adun.test_abstract;

import org.springframework.stereotype.Service;

/**
 * @author zhudunfeng
 * @date 2021/6/10 13:56
 */
public interface OneService {
    public void print();

    public default void printOne(){
        System.out.println("哈哈哈哈");
    }
}
