package com.adun.design_mode_chain01.service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ADun
 * @date 2022/8/7 12:01
 * 这部分是把由谁审核的和审核的单子ID作为唯一key值记录到内存Map结构中。
 */
public class AuthService {
    private static Map<String, Date> authMap = new ConcurrentHashMap<>();

    public static Date queryAuthInfo(String uId,String orderId){
        return authMap.get(uId.concat(orderId));
    }

    public static void auth(String uId,String orderId){
        authMap.put(uId.concat(orderId), new Date());
    }
}
