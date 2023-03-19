package com.adun.redis;

import com.adun.redis.entitys.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhudunfeng
 * @date 2020/11/3 18:42
 */
@SpringBootTest
public class RedisStringTest {


    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "cart:item:";


    @Test
    public void saveRedis() {
        redisTemplate.opsForValue().set(KEY_PREFIX + 1, "哈哈哈");
    }

    @Test
    public void saveRedis2() {
        redisTemplate.opsForValue().set(KEY_PREFIX + 2, "哈哈哈", 30, TimeUnit.SECONDS);
    }

    @Test
    public void getRedis() {
        String s = redisTemplate.opsForValue().get(KEY_PREFIX);
        System.out.println(s);
    }

    @Test
    public void getRedis1() {
        String s = redisTemplate.opsForValue().get("cart:item:");
        Long expire = redisTemplate.getExpire("cart:item:");
        System.out.println(s);
    }

    @Test
    public void getRedis2() {
        Set<String> keys = redisTemplate.keys("cart:item:*");
        System.out.println("keys = " + keys);
        //方式一
        List<String> list = redisTemplate.opsForValue().multiGet(keys);
        System.out.println("list = " + list);

        //方式二
        List<Object> list1 = this.redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection conn = (StringRedisConnection) connection;
                for (String key : keys) {
                    conn.get(key);
                }
                return null;
            }
        });
        System.out.println("list1 = " + list1);


        //方式三
        List<String> list2 = this.redisTemplate.execute(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection conn = (StringRedisConnection) connection;
                List<String> resultList = new ArrayList<>();
                for (String key : keys) {
                    resultList.add(conn.get(key));
                }
                return resultList;
            }
        });
        System.out.println("list2 = " + list2);

//        BoundValueOperations<String, String> stringStringBoundValueOperations = redisTemplate.boundValueOps("cart:");
//        RedisOperations<String, String> operations = stringStringBoundValueOperations.getOperations();
//        Set<String> keys = operations.keys("*");
//        System.out.println("keys = " + keys);
    }


    @Test
    public void removeVal() {
        Boolean delete = redisTemplate.delete(KEY_PREFIX);
        System.out.println(delete);
    }

    @Test
    public void opsObject() {
        Student student = new Student();
        student.setId("1");
        student.setName("张三");
        student.setAddress("北京");

    }


}
