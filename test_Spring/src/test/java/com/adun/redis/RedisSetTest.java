package com.adun.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhudunfeng
 * @date 2021/6/3 14:37
 */
@SpringBootTest
public class RedisSetTest {

    private static final String KEY_PREFIX_TEST="test:";

    private final String key=KEY_PREFIX_TEST+"set";


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void opsSetAdd(){
        Long count = redisTemplate.opsForSet().add(key, "11", "22", "33");
        System.out.println(count);
    }

    /**
     * Set<HK> keys(H key);
     * 获取key所对应的散列表的key
     */
    @Test
    public void members(){
        Set<String> members = redisTemplate.opsForSet().members(key);
        System.out.println(members);
    }

    @Test
    public void values(){
        BoundSetOperations<String, String> operations = redisTemplate.boundSetOps(key);
        System.out.println(operations.members());
    }


    /**
     *
     Map<HK, HV> entries(H key);
     获取整个哈希存储根据密钥

     */
    @Test
    public void  entries(){
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        System.out.println(entries);
    }

}
