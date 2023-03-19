package com.adun;

import com.adun.log.LogContainer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@EnableAspectJAutoProxy
@SpringBootApplication
@MapperScan("com.adun.mapper")
//@PropertySource("banner.txt")
//@PropertySource("")
//@ConfigurationPropertiesScan("")
public class DemoApplication {

    public static void main(String[] args) {
//        ApplicationContext run = SpringApplication.run(DemoApplication.class, args);
//        ServiceAA serviceAA = run.getBean("serviceAA",ServiceAA.class);
//        ServiceBB serviceBB = run.getBean("serviceBB",ServiceBB.class);
        ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
        LogContainer.log("哈哈哈哈");
        LogContainer.log("哈哈哈哈");
        LogContainer.log("哈哈哈哈");

        DemoApplication bean = ctx.getBean(DemoApplication.class);
        bean.test();


//        try {
//            TimeUnit.SECONDS.sleep(5);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ctx.close();
    }

    public  void test(){
        URL resource = this.getClass().getClassLoader().getResource("\\banner.txt");
        try {
            System.out.println(resource.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String replace = this.getClass().getPackage().getName().replace(".", "/");
        System.out.println(this.getClass().getClassLoader().getResource(replace));

        System.out.println(this.getClass().getClassLoader().getResource("./"));

    }


}
