package com.adun.handwritten.hashmap;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @author ADun
 * @date 2022/8/9 10:12
 */
public class MyHashMap<K, V> implements MyMap<K,V> {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("aa", "bb");
        map.put("aa", "xx");
        map.put("aa", "yy");
        System.out.println(map.get("aa"));

        MyMap<String, Object> myHashMap = new MyHashMap<>();
        myHashMap.put("aa", "bb");
        myHashMap.put("aa", "xx");
        myHashMap.put("aa", "yy");
        myHashMap.put("cc", "dd");
        myHashMap.put("ee", "ff");
        System.out.println(myHashMap.get("aa"));
        System.out.println(myHashMap.get("cc"));
        System.out.println(myHashMap.get("ee"));
        System.out.println(myHashMap.remove("cc"));
        System.out.println(myHashMap.get("aa"));
    }

    private Entry[] table = null;
    private int size = 0;

    public MyHashMap() {
        this.table = new Entry[16];
    }


    @Override
    public int size() {
        return this.size;
    }

    /**
     * @param k
     * @param v
     * @return
     */
    @Override
    public V put(K k, V v) {
        int index = hash(k);
        int hash = k.hashCode();
        Entry<K, V> entry = table[index];
        if (null == entry) {
            table[index] = new Entry<>(k, v, null, hash);
            size++;
        } else {
            //头插法
            //table[index] = new Entry<>(k, v, entry, hash);
            //尾插法
            Entry<K,V> tmp = entry;
            while (tmp.next!=null){
                //找到链表尾部
                tmp = tmp.next;
            }
            tmp.next = new Entry<>(k, v, null, hash);
            size++;
        }
        return (V) table[index].getValue();
    }

    /**
     * 1. 通过key 进行hash 计算得到index
     * 2. 根据index  判断是否为空，如果为空就直接返回null。
     * 3. 如果不为空，有查询的key与当前key 进行比较,如果当前节点的next是否为空，那么就返回当前节点，如果为不为空那么就取遍历next,直到相等为止。
     * 4. 如果相等就直接返回数据。
     *
     * @param k
     * @return
     */
    @Override
    public V get(K k) {
        int index = hash(k);
        int hash = k.hashCode();
        Entry<K, V> entry = table[index];
        if (null == entry) {
            return null;
        } else {
            //头插法查询
//            if (entry.getKey() == k && entry.hash == hash) {
//                return entry.getValue();
//            }
//            Entry<K, V> next = entry.next;
//            while (next != null) {
//                if (next.getKey() == k && next.hash == hash) {
//                    return next.getValue();
//                }
//                //向下遍历链表
//                next = next.next;
//            }

            //尾插法查询
            if (entry.getKey() == k && entry.hash == hash) {
                //查询到链表尾部
                Entry<K,V> tmp = entry;
                while (tmp.next!=null){
                    tmp=tmp.next;
                }
                return tmp.getValue();
            }
        }
        return null;
    }

    private int hash(K k) {
        int index = k.hashCode() % (this.table.length - 1);
        if (index < 0) {
            // 如果取的到为负数
            return -index;
        }
        return index;
    }



    @Override
    public V remove(K k) {
        int index = hash(k);
        int hash = k.hashCode();
        Entry<K, V> entry = table[index];
        if (entry == null) {
            return null;
        } else {
            if (entry.getKey() == k && entry.hash == hash) {
                // 如果是头节点，直接移除该元素
                entry = entry.next;
                table[index] = entry;
                return (V) table[index];
            }

            //如果查询运算不是头节点，需要遍历链表
            if (entry.next != null) {
                Entry<K, V> head = entry;
                //分身遍历指针
                Entry<K, V> p = head;
                Entry<K, V> next = head.next;
                do {
                    if (next.getKey() == k && next.hash == hash) {
                        // 删除该节点, 前一个节点的next 指向该节点的next
                        //Entry<K,V> temp = p.next;
                        p.next = next.next;
                        break;
                        //return (V)temp.getValue();
                    }
                    p = next;
                    next = next.next;
                } while (next != null);
            } else {
                //数组
                table[index] = null;
            }
            return table[index] == null ? null : (V) table[index].getValue();
        }
    }


    class Entry<K, V> {
        protected int hash;
        protected K k;
        protected V v;
        //链表指针
        Entry<K, V> next;

        public Entry(K k, V v, Entry<K, V> next, int hash) {
            this.k = k;
            this.v = v;
            this.next = next;
            this.hash = hash;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }
    }
}
