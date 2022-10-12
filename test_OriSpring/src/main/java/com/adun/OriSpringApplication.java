package com.adun;

import com.adun.bean.A;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jca.context.ResourceAdapterApplicationContext;

/**
 * @author ADun
 * @date 2022/8/6 20:44
 */
@ImportResource("conf/applicationContext.xml")
@EnableSpringConfigured
@ComponentScan
public class OriSpringApplication {
    public static void main(String[] args) {
        /*ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");*/
        //ConfigurableApplicationContext cac=new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        AnnotationConfigApplicationContext cac = new AnnotationConfigApplicationContext(OriSpringApplication.class);
        A a = cac.getBean("a",A.class);
        System.out.println(a.toString());
    }
}
