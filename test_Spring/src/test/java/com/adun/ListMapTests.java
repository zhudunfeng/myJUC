package com.adun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ADun
 * @date 2022/11/23 21:24
 */
@SpringBootTest
public class ListMapTests {


    @Test
    public void test() {

        System.out.println(ThreadLocal.withInitial(() ->
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        ).get().format(new Date()));
        System.out.println(ThreadLocal.withInitial(() ->
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        ).get().format(new Date()));
        //mp1
        List<Map<String, Object>> listMap1 = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        //1h
        map1.put("aa", 1);
        map1.put("bb", 2);
        map1.put("cc", 3);
        listMap1.add(map1);

//        System.out.println(map1.get("ff"));

        //mp2
        List<Map<String, Object>> listMap2 = new ArrayList<>();
        Map<String, Object> map2 = new HashMap<>();
        //1h
        map2.put("aa", 4);
        map2.put("dd", 5);
        listMap2.add(map2);

        for (Map<String, Object> map : listMap2) {

        }

        //mp3
        List<Map<String, Object>> listMap3 = new ArrayList<>();
        Map<String, Object> map3 = new HashMap<>();
        map3.put("aa", 3);
        map3.put("bb", 4);
        listMap1.add(map3);
    }


}
