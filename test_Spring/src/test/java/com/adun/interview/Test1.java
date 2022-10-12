package com.adun.interview;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author ADun
 * @date 2022/7/25 12:11
 * <p>
 * 类型转换面试题
 * <p>
 * 最终的输出结果是()
* A.  10 3或3 10
 * B. 编译错误
 * C. 第6行出现运行错误【TreeSet会出现】
 * D. 第10行出现运行错误【HashSet会出现】
 */
public class Test1 {
    public static void main(String[] args) {
        //HashSet set = new HashSet();
        TreeSet set = new TreeSet();
        set.add(10);
        //TreeSet
        //Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.Double
        set.add(3.5);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            //hashSet
            //Exception in thread "main" java.lang.ClassCastException: java.lang.Double cannot be cast to java.lang.Integer
            int i = (int) iterator.next();
            System.out.print(i + " ");
        }
    }
}
