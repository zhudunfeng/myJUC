package com.adun;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Zhu Dunfeng
 * @date 2021/11/22 17:30
 */
@SpringBootTest
public class JavaCopyTest {


    @Test
    public void testCopy01() {
        Address address = new Address();
        address.setAddress("北京");
        Student aa = new Student("1", "aa", 10, address);
        Student bb = new Student();
        System.out.println("aa1" + aa);
        //这是浅拷贝，如果是对象，拷贝的是地址
        BeanUtils.copyProperties(aa, bb);
        bb.setName("bb");
        //Address address1 = new Address("上海");
        bb.getAddress().setAddress("上海");
        System.out.println("aa2" + aa);
        System.out.println("bb" + bb);
    }

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 使用序列化实现对象的深拷贝
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testCopy02() throws JsonProcessingException {
        Address address = new Address();
        address.setAddress("北京");
        Student aa = new Student("1", "aa", 10, address);
        System.out.println("aa1" + aa);
        //这是浅拷贝，如果是对象，拷贝的是地址
        //Student bb = new Student();
        //BeanUtils.copyProperties(aa, bb);
        //使用json序列化进行深拷贝
        Student bb = objectMapper.readValue(objectMapper.writeValueAsString(aa), Student.class);
        bb.setName("bb");
        //Address address1 = new Address("上海");
        bb.getAddress().setAddress("上海");
        System.out.println("aa2" + aa);
        System.out.println("bb" + bb);

        String s = objectMapper.writeValueAsString(bb);
        System.out.println(s);
        Student cc = objectMapper.readValue(s, Student.class);
        System.out.println("cc=" + cc);
    }


    /**
     * 使用Cloneable接口实现对象的深拷贝
     */
    @Test
    public void testCopy03() throws CloneNotSupportedException {
        Student aa = new Student("1", "张三", 18,
                new Address("北京", 200, null));
        System.out.println("aa1" + aa);

        Student bb = aa.clone();
        bb.setId("2");
        bb.setName("李四");
        bb.getAddress().setAddress("上海");

        System.out.println("aa2" + aa);
        System.out.println("bb" + bb);
    }

}
