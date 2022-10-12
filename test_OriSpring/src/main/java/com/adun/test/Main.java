package com.adun.test;

import com.adun.bean.A;
import com.adun.bean.B;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author zhudunfeng
 * @date 2021/6/7 16:26
 */
public class Main {
    private String name;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        A a = applicationContext.getBean("a", A.class);
        B b = applicationContext.getBean("b", B.class);
        Main main = new Main();
    }
}
