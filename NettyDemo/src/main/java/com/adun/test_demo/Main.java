package com.adun.test_demo;

/**
 * @author zhudunfeng
 * @date 2020/11/15 0:06
 */
public class Main{
    static int value = 33;

    static {

    }

    public static void main(String[] args) throws Exception{
        new Main().printValue();
    }

    private void printValue(){
        int value = 3;//局部变量
        //避免通过一个类的对象引用访问此类的静态变量或静态方法，无谓增加编译器解析成本，直接用类名来访问即可。
//        System.out.println(this.value);
        System.out.println(value);
    }
}
