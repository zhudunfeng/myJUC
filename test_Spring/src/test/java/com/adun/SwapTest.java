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
        //自动拆箱
        System.out.println(a);
        System.out.println(b);

        Integer c = new Integer(30);
        Integer d = new Integer(40);
        swap(c,d);
        System.out.println(c);
        System.out.println(d);
    }


    public void swap(Integer a,Integer b){
        Integer tmp=a;
        a=b;
        b=tmp;
    }

}
