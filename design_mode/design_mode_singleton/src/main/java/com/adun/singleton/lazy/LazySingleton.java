package com.adun.singleton.lazy;

/**
 * @author ADun
 * @date 2022/11/10 20:55
 */
public class LazySingleton {

    private static volatile LazySingleton lazySingleton = null;

    private LazySingleton() {
        System.out.println("我是lazy");
    }

    public static LazySingleton getLazySingleton() {
        if (lazySingleton == null) {
            //对当前类模板上锁
            synchronized (LazySingleton.class) {
                if (lazySingleton == null) {
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}
