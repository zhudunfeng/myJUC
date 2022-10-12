package com.adun.test_threadlocal;

/**
 * @author ADun
 * @date 2022/4/27 11:14
 */
public class ThreadLocalTest {

    public static final ThreadLocal<Integer> threadLocal = new ThreadLocal();
    public static final ThreadLocal<Integer> threadLocal2 = new ThreadLocal();

    public static void main(String[] args) {
        threadLocal.set(1234);
        Integer num1 = threadLocal.get();
        System.out.println(num1);
        threadLocal.remove();
        Integer num2 = threadLocal.get();
        System.out.println(num2);

        threadLocal2.set(111);
        System.out.println(threadLocal2.get());
        threadLocal2.remove();
    }
}
