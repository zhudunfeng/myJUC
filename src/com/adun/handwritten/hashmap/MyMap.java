package com.adun.handwritten.hashmap;

/**
 * @author ADun
 * @date 2022/8/9 15:30
 */
public interface MyMap<K,V> {
    int size();

    V put(K k,V v);

    V get(K k);

    V remove(K k);

    default int timeout(){return -1;};

    interface Entry<K,V>{
        K getKey();

        V getValue();
    }
}
