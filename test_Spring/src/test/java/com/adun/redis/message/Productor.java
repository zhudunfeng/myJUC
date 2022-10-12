package com.adun.redis.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Zhu Dunfeng
 * @date 2022/5/9 21:50
 */
@SpringBootTest
public class Productor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void sendMessage(){
    }
}
