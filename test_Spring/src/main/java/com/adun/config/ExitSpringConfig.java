package com.adun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Zhu Dunfeng
 * @date 2022/1/1 20:13
 */
@Component
public class ExitSpringConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostConstruct
    public void startPrint(){
        redisTemplate.opsForValue().set("key:aa:", "bb");
        String s = redisTemplate.opsForValue().get("key:aa:");
        System.out.println(s);
        System.out.println("=============start=============");
    }

    @PreDestroy
    public void exit(){
        String s = redisTemplate.opsForValue().get("key:aa:");
        System.out.println(s);
        redisTemplate.delete("key:aa:");
        System.out.println("==============exit==============");
    }

}
