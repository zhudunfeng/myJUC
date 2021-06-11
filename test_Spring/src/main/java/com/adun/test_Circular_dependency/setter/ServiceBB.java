package com.adun.test_Circular_dependency.setter;

import org.springframework.stereotype.Component;

/**
 * @author zhudunfeng
 * @date 2021/6/1 17:52
 */
@Component
public class ServiceBB {

    private ServiceAA serviceAA;

    public void setServiceAA(ServiceAA serviceAA){
        this.serviceAA=serviceAA;
        System.out.println("BB 设置了 AA");
    }

}
