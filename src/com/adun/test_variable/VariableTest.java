package com.adun.test_variable;

import org.junit.Test;

/**
 * @author zhudunfeng
 * @date 2021/6/8 17:13
 */
public class VariableTest {

    private int i;

    private boolean flag;

    @Test
    public void print() {
        System.out.println(i);
        System.out.println(flag);
    }

    @Test
    public void scope(){
        int a=10;
        {
            int s=11;
            System.out.println(s);
//            int a=11;
        }
    }

    /**
     * ①如果是引用类型，==比较的是两个对象的引用是否完全相同，如果是基本类型，比较的是两个基本类型的数值是否相同。
     * ②如果没有重写的话，equals默认按照 ==进行比较，如果重写了equals()方法，则按照对应的比较规则比较。
     * ③两个对象如果相等，那么它们的hashCode值必须相等，但两个对象的hashCode值相等时，它们不一定相同。
     */
    @Test
    public void compareRefAddress() {
        Integer f1 = 100, f2 = 100, f3 = 128, f4 = 128;

//
        System.out.println(f1 == f2);//-128-127
        System.out.println(f3 == f4);

        System.out.println(f3.equals(f4));

//        int f1=100,f2=100,f3=150,f4=150;
//
//        System.out.println(f1 == f2);
//        System.out.println(f3 == f4);
    }


}
