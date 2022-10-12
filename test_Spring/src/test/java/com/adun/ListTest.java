package com.adun;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhudunfeng
 * @date 2021/6/16 9:51
 */
@SpringBootTest
public class ListTest {

    @Test
    public void arrayToList(){
        Object[] array =new String[]{"aa","bb","cc"};
        System.out.println(Arrays.asList(array).toString());
        System.out.println("aaa");
        System.out.println(1);
        System.out.println(JSON.toJSON("aaa"));
        System.out.println(JSON.toJSON(1));
        System.out.println("=====================");
        for (Object o : array){
            if(o instanceof String){
                String s=(String)o;
                System.out.println(s);
            }else if(o instanceof Integer){
                Integer i=(Integer)o;
                System.out.println(i);
            }
        }
    }

    @Test
    public void ListTest01() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        System.out.println(list);
    }

    @Test
    public void ListTest02() {
        List<Student> list = new ArrayList<>();
        Student aa = new Student("1", "aa", 15);
        Student bb = new Student("2", "bb", 15);
        Student cc = new Student("3", "cc", 15);
        list.add(aa);
        list.add(bb);
        list.add(cc);

        System.out.println(list);
    }

    //测试浅拷贝与深拷贝
    @Test
    public void ListTest03() {
        List<Student> list = new ArrayList<>();
        Student aa = new Student("1", "aa", 15);
        Student bb = new Student("2", "bb", 15);
        Student cc = new Student("3", "cc", 15);
        list.add(aa);
        list.add(bb);
        list.add(cc);

        list.forEach(System.out::println);
        List<Student> list2 = new ArrayList<>();
        //这是浅拷贝
        //BeanUtils.copyProperties(list, list2);
        list2.addAll(list);
        System.out.println("==================");
        list2.forEach(student -> {
            if ("1".equals(student.getId())) {
                student.setAge(20);
            }
        });
        list2.forEach(System.out::println);


    }


    @Test
    public void subListTest() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        list.add("ff");
        list.add("gg");
        System.out.println(list.subList(0, 4));
        list.subList(0, 4).clear();
        System.out.println(list);

    }


    @Test
    public void mergeList() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(5);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(4);
        list2.add(6);

        List<String> collect = list1.stream().map(String::valueOf).collect(Collectors.toList());

        String join = StringUtils.join(collect, ",");
        System.out.println(join);

        String join1 = String.join(",", collect);
        System.out.println(join1);

    }


    @Test
    public void retainAllList() {
        List<String> list1 = new ArrayList<>();
        list1.add("aa");
        list1.add("bb");
        list1.add("cc");

        List<String> list2 = new ArrayList<>();
//        list2.add("cc");
        list2.add("dd");
        list2.add("ff");

        boolean b = list1.retainAll(list2);
        System.out.println("aa:" + list1);
        System.out.println("aa:" + list2);
        System.out.println("aa:" + b);

    }

    @Test
    public void testList() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        System.out.println(list1);

        List<Long> collect = list1.stream().map(Long::valueOf).collect(Collectors.toList());
        System.out.println(collect);

        Integer a = Integer.MAX_VALUE;
        System.out.println(a);

        long longV = Long.MAX_VALUE;
        System.out.println(longV);

        Long l = 4500421330L;

        System.out.println(l);
        System.out.println(l.toString());
//        System.out.println(s);
//
        System.out.println(Integer.valueOf(l.toString()));

    }


    @Test
    public void testList1() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        List<Integer> integers = list1.subList(0, 2);
        System.out.println(list1 == integers);
    }


}
