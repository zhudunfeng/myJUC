package com.adun.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExitCode implements DisposableBean {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void destroy() throws Exception {
        String s = redisTemplate.opsForValue().get("key:aa:");
        System.out.println(s);
        redisTemplate.delete("key:aa:");
        System.out.println("执行ExitCode中的退出代码");
    }
}
