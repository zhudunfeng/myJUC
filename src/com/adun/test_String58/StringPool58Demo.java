package com.adun.test_String58;

import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhudunfeng
 * @date 2021/3/12 15:09
 */
public class StringPool58Demo {
    public static void main(String[] args) {
        String str1 = new StringBuilder().append("58").append("tongcheng").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        System.out.println();


        /*
        * java
        * java
        * false 特别：只有java是false
        * */
        String str2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());

    }


    @Test
    public void test1(){
        String s1="hello";
        String s2="hello";
        //地址相同，s2获取到s1放入常量池中的常量【由此可看出，s1只在常量池中创建了一个对象】
        System.out.println(s1 == s2);
        //s.intern()是显示的直接获取字符串常量池中的常量，如果获取的常量不存在，则创建
        System.out.println(s1 == s2.intern());
    }

    @Test
    public void printFile() throws IOException {
        FileInputStream fis = new FileInputStream("src/com/adun/test_String58/StringPool58Demo.java");
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();
        String s = new String(bytes);
        System.out.println(s);
    }

    @Test
    public void fileCopy() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        byte[] bytes = new byte[1024];
        InputStream inputStream = classLoader.getResourceAsStream("com/adun/test_String58/file.txt");
        try(FileOutputStream outputStream = new FileOutputStream("file2.txt")) {
//            inputStream
            int read=0;
            while ((read=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,read);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileCopyChar() throws UnsupportedEncodingException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        byte[] bytes = new byte[1024];
        InputStream inputStream = classLoader.getResourceAsStream("com/adun/test_String58/file.txt");
        InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("file3.txt"),"UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            while (bufferedReader.ready()) {
                bufferedWriter.write(bufferedReader.readLine()+"\n");
                bufferedWriter.flush();
            }
            bufferedReader.close();
            reader.close();
            inputStream.close();
            bufferedWriter.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void sortString(){
        List<Object> list = new ArrayList<>();
//        list.add("哈哈")


    }
}
