package com.adun.test_Circular_dependency.constructor;

import org.springframework.stereotype.Component;

/**
 * @author zhudunfeng
 * @date 2021/5/21 0:10
 */

@Component
public class ServiceB {

    private ServiceA serviceA;

    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
