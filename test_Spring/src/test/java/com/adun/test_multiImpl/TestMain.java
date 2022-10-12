package com.adun.test_multiImpl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ADun
 * @date 2022/8/27 16:09
 */
@SpringBootTest
public class TestMain {


    @Test
    public void testMain(){
        A a=new C();
        C c=(C)a;
        c.test();
    }

}
