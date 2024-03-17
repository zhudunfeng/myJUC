package com.adun.test_fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Map;

/**
 * @author ADun
 * @date 2023/6/5 9:11
 */
@SpringBootTest
public class BeanToJsonTests {


    /**
     * bean转camelJson
     */
    @Test
    public void beanToCamelJson() {
        Student stu = new Student()
                .setUserName("张三")
                .setPhoneNumber("111")
                .setAddress(Arrays.asList("北京","上海"));
        String camelJson = JSON.toJSONString(stu);
        System.out.println(String.format("camelJson:%s\n", camelJson));
    }


    /**
     * bean转snakeJson
     */
    @Test
    public void beanToSnakeJson() {
        Student stu = new Student()
                .setUserName("张三")
                .setPhoneNumber("111")
                .setAddress(Arrays.asList("北京","上海"));
//        SerializeConfig serializeConfig = new SerializeConfig();
        //使用单例模式，避免性能问题
        SerializeConfig serializeConfig = SerializeConfigSingleton.getInstance();
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        String snakeJson = JSON.toJSONString(stu, serializeConfig);
        System.out.println(String.format("snakeJson:%s\n", snakeJson));
    }

    /**
     * camelBean ->snakeJson-> snakeMap
     */
    @Test
    public void camelBeanToSnakeMap() {
        Student stu = new Student()
                .setUserName("张三")
                .setPhoneNumber("111")
                .setAddress(Arrays.asList("北京","上海"));
        String camelJson = JSON.toJSONString(stu);
        System.out.println(String.format("camelJson:%s\n", camelJson));
        //使用单例模式，避免性能问题
        SerializeConfig serializeConfig = SerializeConfigSingleton.getInstance();
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        //转蛇形json
        String snakeJson = JSON.toJSONString(stu, serializeConfig);
        Map map = JSON.parseObject(snakeJson, Map.class);
        System.out.println(map);
    }


    /**
     * json的key是蛇形时，bean的属性是驼峰时，会自动转成驼峰
     * camelBean ->snakeJson-> snakeMap
     */
    @Test
    public void snakeJsonToCamelBean() {
        Student stu = new Student()
                .setUserName("张三")
                .setPhoneNumber("111")
                .setAddress(Arrays.asList("北京","上海"));
        String camelJson = JSON.toJSONString(stu);
        System.out.println(String.format("camelJson:%s\n", camelJson));
        //使用单例模式，避免性能问题
        SerializeConfig serializeConfig = SerializeConfigSingleton.getInstance();
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        //转蛇形json
        String snakeJson = JSON.toJSONString(stu, serializeConfig);
//        JSONObject jsonObject = JSON.parseObject(snakeJson, JSONObject.class);
        JSONObject jsonObject = JSON.parseObject(snakeJson, new TypeReference<JSONObject>() {
        });
        Student student = JSON.toJavaObject(jsonObject, Student.class);
        System.out.println(student);
    }


}
