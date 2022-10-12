package com.adun.test_reflect;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author ADun
 * @date 2022/8/23 12:34
 */
public class Demo {

    @MyAnnotation("zzt")
    public ArrayList<String> test(String s) throws NullPointerException{
        System.out.println("test方法执行了！");
        return null;
    }

}
