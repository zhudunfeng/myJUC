package com.adun.test_oom;

/**
 * @author zhudunfeng
 * @date 2020/9/12 23:09
 * 栈溢出demo【递归极其容易出现】
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        //Exception in thread "main" java.lang.StackOverflowError
        // 这是一个错误，不属于Exception【工作中交流会说成异常】
        stackOverflowError();
    }

    private static void stackOverflowError() {
        //递归，不断产生栈帧
        stackOverflowError();
    }
}
