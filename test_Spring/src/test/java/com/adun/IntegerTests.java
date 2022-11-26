package com.adun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ADun
 * @date 2022/11/14 0:02
 */
@SpringBootTest
public class IntegerTests {

    @Test
    public void testIntegerPool(){
        Integer a1 = 127, a2 = 127,
                b1 = 128, b2 = 128;

        //比较的是地址 -128-127 整形池
        System.out.println(a1 == a2);
        System.out.println(b1 == b2);

        System.out.println(a1.equals(a2));
        System.out.println(b1.equals(b2));

        Integer c1=-128,c2=-128;
        System.out.println(c1==c2);
        System.out.println(c1.equals(c2));
    }

}
