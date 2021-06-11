package com.adun;

import com.adun.test_Circular_dependency.setter.ServiceAA;
import com.adun.test_Circular_dependency.setter.ServiceBB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        ServiceAA serviceAA = run.getBean("serviceAA",ServiceAA.class);
        ServiceBB serviceBB = run.getBean("serviceBB",ServiceBB.class);
    }

}
