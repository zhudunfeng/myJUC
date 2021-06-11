package com.adun.redis;

import com.adun.redis.entitys.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author zhudunfeng
 * @date 2020/11/3 18:42
 */
@SpringBootTest
public class RedisStringTest {


    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX="cart:item:";






    @Test
    public void saveRedis(){
        redisTemplate.opsForValue().set(KEY_PREFIX, "哈哈哈");
    }

    @Test
    public void getRedis(){
        String s = redisTemplate.opsForValue().get(KEY_PREFIX);
        System.out.println(s);
    }

    @Test
    public void removeVal(){
        Boolean delete = redisTemplate.delete(KEY_PREFIX);
        System.out.println(delete);
    }

    @Test
    public void opsObject(){
        Student student = new Student();
        student.setId("1");
        student.setName("张三");
        student.setAddress("北京");

    }









}
