package com.adun;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhudunfeng
 * @date 2021/6/4 22:50
 */
@SpringBootTest
public class StringTest {

    public <T> T getObject(T value){
        return (T)value;
    }

    public <T,R> R getObject1(T value,R r){
        return (R)value;
    }



    @Test
    public void  testGet(){
        String a = "a";
        String b = getObject(a);
        System.out.println(b);
        Student student = new Student();
        student.setName("张三");
        Student student1 = getObject(student);
        System.out.println(student1);

    }

    @Test
    public void compareString() {
        String a = "a";
        String b = "a";

        String java = "java";
        String java1 = "java";

        System.out.println(a == b);
        System.out.println(java == java1);

        Integer x1=5;
        Long x2=Long.valueOf(x1);
        System.out.println(x2);

    }

    @Test
    public void compareStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder("Mei").append("Tuan");
        String str1 = stringBuilder.toString();

        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

    }


    @Test
    public void add() {
        double sum = sum(150, 20);
        System.out.println(sum);
    }

    public double sum(double num, int years) {

        for (int i = 1; i <= years; i++) {
            num = num * (1 + 0.05);
        }
        return num;
    }

    @Test
    public void compareDup() {
        Student aa = new Student("1", "aa", 11);
        Student bb = new Student("1", "aa", 11);
        List<Student> students = new ArrayList<>();
        students.add(aa);
        students.add(bb);

        List<Student> collect = students.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void dec() throws UnsupportedEncodingException {
        String comment = "%E5%8F%AF%E4%BB%A5%E8%AF%95%E7%94%A8%20%EF%BC%8C%E5%9C%A8%E4%BF%9D%E8%AF%81%E4%BA%A7%E5%93%81%E5%AE%89%E5%85%A8%E5%8F%AF%E6%8E%A7%E7%9A%84%E6%83%85%E5%86%B5%E4%B8%8B%EF%BC%8C%E5%8F%A6%E5%A4%96%E5%8F%AF%E4%BB%A5%E4%BB%8E%E4%B8%80%E4%B8%AA%E9%83%A8%E9%97%A8%E8%BF%9B%E8%A1%8C%E8%AF%95%E7%94%A8%EF%BC%8C%E5%A6%82%E6%9E%9C%E5%8F%AF%E4%BB%A5%E7%9A%84%E8%AF%9D%EF%BC%8C%E5%8F%AF%E4%BB%A5%E8%BF%9B%E8%A1%8C%E5%A4%A7%E8%8C%83%E5%9B%B4%E5%86%85%E7%9A%84%E6%8E%A8%E5%B9%BF%E5%92%8C%E4%BD%BF%E7%94%A8";
        String decode = URLDecoder.decode(comment, "UTF-8");
        System.out.println(decode);
    }


    @Test
    public void joinString() {
        String[] array = {"aa", "bb", "cc"};

        String collect = Arrays.stream(array).flatMap(s -> Stream.of(String.join(",", s))).collect(Collectors.joining(","));
        System.out.println(collect);
    }


    @Test
    public void testVal() {
        boolean flag = true;

        if (flag = false) {
            System.out.println("aa");
        } else if (flag == false) {
            System.out.println("bbbb");
        }

    }

    @Test
    public void testNum() {
        double a = 2.0, b = 4.0, c = 2.0;

        double m = Math.pow(b, 2) - 4 * a * c;
        if (m > 0) {
            double x1 = (-b + Math.sqrt(m)) / 2 * a;
            double x2 = (-b - Math.sqrt(m)) / 2 * a;
            System.out.printf("x1=%f x2=%3.2f \n", x1, x2);
        } else if (m == 0) {
            double x1 = (-b + Math.sqrt(m)) / 2 * a;
            System.out.printf("x1=x2=%3.2f \n", x1);
        } else {
            System.out.println("无解");
        }
    }

    /**
     * 遍历字符串
     */
    @Test
    public void testString() {
        String s = "abcdef!中国";
//        String[] split = s.split("|");
//        for (String s1 : split) {
//            System.out.println(s1);
//        }
        String s1 = new String(s.getBytes(), Charset.defaultCharset());
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i));
        }

//        byte[] bytes = s.getBytes();
//        for (byte aByte : bytes) {
//            System.out.println((char) aByte);
//        }
    }

    @Test
    public void testOut() {
        //Java没有goto跳转，作为保留关键字
        System.out.println("ok1");
        //goto label1;
        System.out.println("ok2");
        System.out.println("ok3");
        System.out.println("ok4");
        label1:
        System.out.println("ok5");
        System.out.println("ok6");

    }


    @Test
    public void testStr() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        List<String> collect = list.stream().map(item -> {
            System.out.println("String:" + item);
            return Integer.valueOf(item);
        }).map(item -> {
            System.out.println("Int:" + item);
            return String.valueOf(item);
        }).collect(Collectors.toList());

        System.out.println(collect);

    }

    @Test
    public void testString1() {
        String string = getString();
        System.out.println(string);
    }

    public String getString() {
        String a = "";
        try {
            a = "哈哈哈";
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Test
    public void testArr() {
        String[] array = {"aa", "bb", "cc", "dd", "ee"};
        System.out.println(Arrays.toString(array));
        changeArr(array);
        System.out.println(Arrays.toString(array));

    }

    public void changeArr(String[] array) {
        array[1] = "ffff";
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void testString2() {
        String a = null;
        String jsonString = a != null ? JSON.toJSONString(a) : null;
        System.out.println(jsonString.toString());
    }

    @Test
    public void testString3() {
        String a = "aa";
        String[] split = a.split(",");
        System.out.println(Arrays.asList(split));
    }

    @Test
    public void testString4() {
        System.out.println(Integer.MAX_VALUE);
    }


    /**
     * 提取等当前字符串中所有的数字
     */
    private String s = "hhhhaaa11ff22rr31";

    //ascii
    @Test
    public void getNumber1() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int c = (int) s.charAt(i);
            if (c >= 48 && c <= 57) {
                numbers.add(Character.getNumericValue((char) c));
            }
        }
        System.out.println(numbers);
    }

    //regex
    @Test
    public void getNumber2() {
        List<Integer> numbers = new ArrayList<>();
        String regEx = "[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(s);
        List<String> strings = Arrays.asList(matcher.replaceAll("").trim().split(""));
        numbers.addAll(strings.stream().map(Integer::valueOf).collect(Collectors.toList()));
        System.out.println(numbers);
    }

    @Test
    public void getNumber3() {
        List<Integer> numbers = new ArrayList<>();
//        String[] split = s.split("[A-Za-z]");
        Pattern pattern = Pattern.compile("[^0-9]");
        List<String> collect = pattern.splitAsStream(s).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        System.out.println(collect);

    }


    @Test
    public void getMinString() {
        String s1 = "aaacffcc";
        String s2 = "aaaaffccff";
        char[] charsArr1 = s1.toCharArray();
        char[] charsArr2 = s2.toCharArray();

        //相同的索引号
        int index = 0;
        for (int i = 0; i < charsArr1.length; i++) {
            if (charsArr1[i] == charsArr2[i]) {
                index++;
            }
        }

        if (index != 0) {
            System.out.println(s1.substring(0, index));
        }
    }

    @Test
    public void toByte64(){
        byte[] encode = Base64.getEncoder().encode("哈哈哈哈哈".getBytes());
        System.out.println(new String(encode,Charset.defaultCharset()));
    }
}
