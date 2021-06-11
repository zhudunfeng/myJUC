package com.adun.test_Circular_dependency.setter;

import org.springframework.stereotype.Component;

/**
 * @author zhudunfeng
 * @date 2021/6/1 17:52
 */
@Component
public class ServiceAA {

    private ServiceBB serviceBB;

    public void setServiceBB(ServiceBB serviceBB){
        this.serviceBB=serviceBB;
        System.out.println("AA 设置了 BB");
    }

}
