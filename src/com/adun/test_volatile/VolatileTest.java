package com.adun.test_volatile;

import org.junit.Test;

/**
 * @Auther ADun
 * @Date 2020/7/18 22:21
 *
 * 不是使用volatile，下面可能出现指令重排的现象【可能运行多次都不会出现】
 */
public class VolatileTest {
    @Test
    public void sortTest(){
        int x=11;//语句1
        int y=12;//语句2
        x=x+5;//语句3
        y=x*x;//语句4
        System.out.println(x);
        System.out.println(y);

    }
}
