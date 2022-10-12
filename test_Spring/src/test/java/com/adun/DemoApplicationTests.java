package com.adun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void test02(){
        List<Integer> integers = Arrays.asList(1, 2, 4, 5, 6, 10);
        integers.forEach(num->{
            System.out.println(num);
            if (num ==5){
                return;
            }
        });

        System.out.println("哈哈哈哈哈哈哈哈哈哈");
    }

    @Test
    public void test01(){
        Long a=null;
//        long a=Long.valueOf("");

        a = a==null ? 0L : a;
        if(a== 0L){
            System.out.println("aaa");
        }

    }
}
