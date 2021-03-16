package com.adun.day02_lock;

/**
 *
 * @author zhudunfeng
 * @date 2021/3/14 23:24
 *
 * 可重入锁：可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫可重入锁。
 *
 * 在一个synchronized修饰的方法或代码块的内部
 * 调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 */
public class ReEnterLockMethondDemo {

    private  String s="1";

    private synchronized void m1(){
        System.out.println("==========外");
        m2();
    }

    private synchronized void m2(){
        System.out.println("==========中");
        m3();
    }

    private synchronized void m3(){
        System.out.println("==========内");
    }



    public static void main(String[] args) {
        new ReEnterLockMethondDemo().m1();
    }
}
