package com.adun.test_Circular_dependency.setter;

/**
 * @author zhudunfeng
 * @date 2021/6/1 17:55
 */
public class ClientSet {
    public static void main(String[] args) {
        ServiceAA serviceAA = new ServiceAA();
        ServiceBB serviceBB = new ServiceBB();

        serviceAA.setServiceBB(serviceBB);
        serviceBB.setServiceAA(serviceAA);
    }
}
