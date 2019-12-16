package com.atguigu.day02_lam;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 1、故障现象
 *  java.util.ConcurrentModificationException
 * 2、导致原因
 *  没加锁+toString 遍历读写不一致if(modCount != expectedModCount)
 * 3、解决方法
 *  3.1 new Vector()
 *  3.2 Collections.synchronizedList(new ArrayList<>());
 *  3.3 new CopyOnWriteArrayList<>();写时复制
 * 4、优化建议
 *
 * 写时复制
 * CopyOnWrite容器即写时复制容器。王一个容器里添加元素的时候，不直接向当前容器Object[]添加，而是将当前容器Object[]进行Copy，
 * 复制出一个新的容器object[] newElements,然后新的容器object[] newElements里添加元素，添加完元素后
 * 再将原容器的引用指向新的容器setArray(newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读，
 * 而不需要加锁，因为当前容器不会添加任何元素。所有CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
 */
public class NoSateListDemo {
    public static void main(String[] args) {
        List<Object> list =new CopyOnWriteArrayList<>(); //new ArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new ArrayList<>();

        for (int i = 1; i <=30 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
