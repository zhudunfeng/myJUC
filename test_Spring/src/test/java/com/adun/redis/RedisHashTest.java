package com.adun.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class RedisHashTest {

    private static final String KEY_PREFIX_TEST="test:";

    private final String key=KEY_PREFIX_TEST+"hash";


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void opsHashPut(){
        HashMap<String, String> map = new HashMap<>();
        map.put("aa", "11");
        map.put("bb", "22");
        map.put("cc", "33");
        redisTemplate.opsForHash().putAll(key,map);

    }

    /**
     * Set<HK> keys(H key);
     * 获取key所对应的散列表的key
     */
    @Test
    public void keys(){

        Set<Object> keys = redisTemplate.opsForHash().keys(key);
        System.out.println(keys);

    }

    @Test
    public void values(){
        List<Object> values = redisTemplate.opsForHash().values(key);
        System.out.println(values);
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
