package com.adun.test_Circular_dependency.constructor;

import org.springframework.stereotype.Component;

/**
 * @author zhudunfeng
 * @date 2021/5/21 0:10
 */
@Component
public class ServiceA {

    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
