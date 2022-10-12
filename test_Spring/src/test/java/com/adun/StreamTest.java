package com.adun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Zhu Dunfeng
 * @date 2021/12/28 11:38
 */
@SpringBootTest
public class StreamTest {

    @Test
    public void streamToStream(){

        List<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("aa,bb,cc");
        stringArrayList.add("dd,ee");
        stringArrayList.add("ff");

        List<String> collect = stringArrayList.stream().distinct()
                .flatMap(oriStream -> Stream.of(oriStream.split(",")))
                .collect(Collectors.toList());
        System.out.println(collect);


        BigDecimal bigDecimal = new BigDecimal("10.1110");
        //四舍五入
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(bigDecimal);


    }


    @Test
    public void testStreamOf(){

        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        List<String> list1 = new ArrayList<>();
        list1.add("cc");
        list1.add("dd");

        List<String> collect = Stream.of(list, list1)
                .flatMap(Collection::stream)
                .filter(s -> !"aa".equals(s))
                .collect(Collectors.toList());
        System.out.println(collect);

    }

}
