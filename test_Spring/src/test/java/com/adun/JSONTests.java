package com.adun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author Zhu Dunfeng
 * @date 2022/3/30 14:36
 */
@SpringBootTest
public class JSONTests {

    @Test
    public void testObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a", 1);
        System.out.println("jsonObject.toString() = " + jsonObject.toString());
        System.out.println("jsonObject.toJSONString() = " + jsonObject.toJSONString());

        Map<String, Object> map=jsonObject;
        System.out.println("map.toString() = " + map.toString());

    }


    @Test
    public void testFastJSON(){
        String jsonString = "[\"a\",\"b\",\"c\",\"d\"]";
        List<String> list = (List) JSONArray.parse(jsonString);
        System.out.println(list);
        list.add("ff");
        System.out.println(list);
        //覆盖目标索引值
        list.set(1, "ee");
        System.out.println(list);

    }


    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper){
        this.objectMapper =objectMapper;
    }

    @Test
    public void testJackson() throws JsonProcessingException {
        String jsonString = "[\"a\",\"b\",\"c\",\"d\"]";
        List list = objectMapper.readValue(jsonString, List.class);
        System.out.println(list);

        list.add("ff");

        String s = objectMapper.writeValueAsString(list);
        System.out.println(s);
    }


}
