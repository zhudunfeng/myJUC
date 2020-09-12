package com.adun.test_oom;

/**
 * @author zhudunfeng
 * @date 2020/9/12 23:27
 * OOM:堆溢出
 */
public class JavaHeapSpaceErrorDemo {
    public static void main(String[] args) {

        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//        String str="adun";
//        while (true){
//            str+= str+new Random().nextInt(111111111)+new Random().nextInt(2222222);
//            str.intern();
//        }

        byte[] bytes = new byte[50 * 1024 * 1024];
    }
}
