package com.adun;

import com.adun.entities.User;
import com.adun.test_mongodb.expand.Person;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Zhu Dunfeng
 * @date 2022/3/9 17:45
 */
@SpringBootTest
public class MapTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void objToMapTest() throws JsonProcessingException {
        Person person = new Person("张三", 18);
//        Map map = JSON.parseObject(JSON.toJSONString(person), Map.class);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        Map map = objectMapper.readValue(objectMapper.writeValueAsString(person), Map.class);
        System.out.println(map);
    }


    @Test
    public void addMultiValueMap(){
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("aa");
        list1.add("bb");
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("cc");
        ArrayList<String> list3 = new ArrayList<>();
        list3.add("dd");
        multiValueMap.put("key1", list1);
        multiValueMap.put("key1", list2);
        multiValueMap.put("key1", list3);
        System.out.println(multiValueMap.get("key1"));

        multiValueMap.addAll("key2", list1);
        multiValueMap.addAll("key2", list2);
        multiValueMap.addAll("key2", list3);
        System.out.println(multiValueMap.get("key2"));
    }

    @Test
    public void testMap1(){
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", "d");
        System.out.println(map.toString());
        System.out.println(JSON.toJSONString(map));

        Map<String, Object> jsonObject = new JSONObject();
        jsonObject.put("aa", "bb");
        System.out.println(jsonObject.toString());
    }

    @Test
    public void testMap2(){
        ArrayList<User> list = new ArrayList<>();
        Map<String, String> collect = list.stream().collect(Collectors.toMap(User::getId, User::getName));
        System.out.println(collect);
    }
}
