package com.adun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zhudunfeng
 * @date 2020/11/8 22:09
 * Java正则表达式
 */
@SpringBootTest
public class RegxTest {
    @Test
    public void match(){
        String s="aa";
        boolean matches = s.matches("[a-zA-z]*");
        System.out.println(matches);

    }

    @Test
    public void pattern(){
        String s="ddXXaaccca";

        Pattern pattern = Pattern.compile("[a]+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()){
            //前闭后开
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            String group = matcher.group();
            System.out.println(group);
        }

    }
    @Test
    public void split_test(){
        String a="aa";
        String[] split = a.split(",");
        List<String> strings = Arrays.asList(split);
        strings.removeAll(Collections.singleton("[]"));
        String collect = strings.stream().collect(Collectors.joining("["));
        System.out.println(collect);
    }

    @Test
    public void replaceString(){
        String s="aaddeedddddddddd111333aaddpp";
        String s1 = s.replaceAll("1", "h");
        System.out.println(s1);
    }
    @Test
    public void split(){
        String[] split = "copy:2:1212509".split(":");
        Arrays.stream(split).forEach(System.out::println);

        String[] s = "a_b".split("_");
        Arrays.stream(s).forEach(System.out::println);
    }
}
