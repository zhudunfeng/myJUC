//package com.adun;
//
//import jdk.internal.org.objectweb.asm.ClassReader;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * ASM技术，JVM不会真正加载类，而是按照jvm规则，对二进制文件进行读取用于判断
// *
// * @author Zhu Dunfeng
// * @date 2022/5/10 21:36
// */
//public class ASMTest {
//
//    public static void main(String[] args) throws IOException {
//        InputStream inputStream = new FileInputStream("D:\\IdeaProjects\\myJUC\\test_Spring\\target\\classes\\com\\adun\\controller\\SampleController.class");
//        ClassReader classReader = new ClassReader(inputStream);
//
//        System.out.println(classReader.getClassName());
//        System.out.println(classReader.getSuperName());
//
//    }
//
//}
