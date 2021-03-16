package com.adun.test_String58;

/**
 * @author zhudunfeng
 * @date 2021/3/12 15:09
 */
public class StringPool58Demo {
    public static void main(String[] args) {
        String str1 = new StringBuilder().append("58").append("tongcheng").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        System.out.println();


        /*
        * java
        * java
        * false 特别：只有java是false
        * */
        String str2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());

    }
}
