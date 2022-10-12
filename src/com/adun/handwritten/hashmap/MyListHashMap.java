package com.adun.handwritten.hashmap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ADun
 * @date 2022/8/9 14:28
 */
public class MyListHashMap<K, V> implements MyMap<K,V> {

    public static void main(String[] args) {
        MyMap<String, Object> stringObjectMyListHashMap = new MyListHashMap<>();
        stringObjectMyListHashMap.put("aa", "cc");
        stringObjectMyListHashMap.put("ee", "cc");
        stringObjectMyListHashMap.put("ff", "ff");
        stringObjectMyListHashMap.remove("ff");
        System.out.println(stringObjectMyListHashMap.get("ee"));
    }




    private List table = null;

    private int size;

    public MyListHashMap() {
        this.table = new LinkedList();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public V put(K k, V v) {
        int hash = k.hashCode();
        table.add(new Entry<>(k, v, hash));
        size++;
        return (V) table.get(size-1);
    }

    @Override
    public V get(K k) {
        int hash = k.hashCode();
        for (int i = size - 1; i >= 0; i--) {
            Entry<K, V> entry = (Entry<K, V>) table.get(i);
            if (entry.k == k && entry.hash == hash) {
                return entry.v;
            }
        }
        return null;
    }

    @Override
    public V remove(K k){
        int hash = k.hashCode();
        List<Entry<K, V>> removes = new ArrayList<>();
        for (int i = size-1; i >=0 ; i--) {
            Entry<K, V> entry = (Entry<K, V>) table.get(i);
            if (entry.k == k && entry.hash == hash) {
                removes.add(entry);
            }
        }
        this.table.removeAll(removes);
        this.size-=removes.size();
        return removes.get(0).v;
    }

    class Entry<K, V> implements MyMap.Entry<K,V> {
        protected int hash;
        protected K k;
        protected V v;

        public Entry(K k, V v, int hash) {
            this.k = k;
            this.v = v;
            this.hash = hash;
        }

        @Override
        public K getKey() {
            return this.k;
        }

        @Override
        public V getValue() {
            return this.v;
        }
    }
}
