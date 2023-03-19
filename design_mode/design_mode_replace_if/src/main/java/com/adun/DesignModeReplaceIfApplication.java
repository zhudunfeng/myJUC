package com.adun;

import com.adun.domain.factory.Factory;
import com.adun.domain.strategy.Handler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesignModeReplaceIfApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignModeReplaceIfApplication.class, args);

//        String nickName2 = "woniu";
//        final Handler invokeStrategy = Factory.getInvokeStrategy(nickName2);
//        invokeStrategy.AA(nickName2);

//        String nickName2 = "woniu1";
//        final AbstractHandler invokeStrategy = Factory2.getInvokeStrategy(nickName2);
//        invokeStrategy.AA(nickName2);

        String nickName2 = "woniu2";
        final Handler invokeStrategy = Factory.getInvokeStrategy(nickName2);
        invokeStrategy.AA(nickName2);
    }

}
