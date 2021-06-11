package com.adun.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhudunfeng
 * @date 2021/6/3 14:37
 */
@SpringBootTest
public class RedisListTest {

    private static final String KEY_PREFIX_TEST="test:";

    private final String key=KEY_PREFIX_TEST+"list";


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void opsList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        redisTemplate.opsForList().leftPushAll(key, list);

    }

    /**
     * Long remove(K key, long count, Object value);
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
     * 计数参数以下列方式影响操作：
     * count> 0：删除等于从头到尾移动的值的元素。
     * count <0：删除等于从尾到头移动的值的元素。
     * count = 0：删除等于value的所有元素。
     */
    @Test
    public void opsListdelete(){

        redisTemplate.opsForList().remove(key, 4L,"aa");

    }

    @Test
    public void opsForListSize(){
        Long size = redisTemplate.opsForList().size(key);
        System.out.println(size);
    }

    /**
     * 	按照索引下标获得元素(从左到右)
     * lrange mylist 0 -1              从0开始，-1表示获取所有
     */
    @Test
    public void opsForListRange(){
        List<String> range = redisTemplate.opsForList().range(key, 0, -1);
        System.out.println(range);
    }

    @Test
    public void opsForListIndex(){
        String index = redisTemplate.opsForList().index(key, 2);
        System.out.println(index);
    }

}
