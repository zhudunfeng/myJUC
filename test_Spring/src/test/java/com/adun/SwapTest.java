package com.adun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Zhu Dunfeng
 * @date 2022/2/21 22:54
 */
@SpringBootTest
public class SwapTest {

    @Test
    public void swapTest(){
        Integer a=10;
        Integer b=20;
        swap(a, b);
        System.out.println(a);
        System.out.println(b);
    }

    public void swap(Integer a,Integer b){
        Integer tmp=a;
        a=b;
        b=tmp;
    }

}
