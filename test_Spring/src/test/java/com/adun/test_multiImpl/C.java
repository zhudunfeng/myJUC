package com.adun.test_multiImpl;

/**
 * @author ADun
 * @date 2022/8/27 16:07
 */
public class C implements A,B {
    String name = "C";
    public void test(){
        System.out.println(B.name);
    }
}
