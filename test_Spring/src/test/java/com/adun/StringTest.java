package com.adun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhudunfeng
 * @date 2021/6/4 22:50
 */
@SpringBootTest
public class StringTest {

    @Test
    public void compareString(){
        String a="a";
        String b="a";

        String java ="java";
        String java1 ="java";

        System.out.println(a==b);
        System.out.println(java==java1);
    }

    @Test
    public void compareStringBuilder(){
        StringBuilder stringBuilder = new StringBuilder("Mei").append("Tuan");
        String str1 = stringBuilder.toString();

        System.out.println(str1.intern()==str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);

    }




}
