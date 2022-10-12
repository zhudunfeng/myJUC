package com.adun.test_abstract;

import org.springframework.stereotype.Service;

/**
 * @author zhudunfeng
 * @date 2021/6/10 13:49
 */
@Service
public class MyService{

    private static String s;

    static {
        s="Hello";
    }


    public void print(){
        System.out.println(s);
    }

}
