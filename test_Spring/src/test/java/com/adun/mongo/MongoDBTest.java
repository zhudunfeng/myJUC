package com.adun.mongo;

import com.adun.test_mongodb.expand.Person;
import com.alibaba.fastjson.JSON;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhudunfeng
 * @date 2021/6/4 9:00
 */
@SpringBootTest
public class MongoDBTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOperations;


    /**
     * 方法说明：将bean转化为另一种bean实体
     *
     * @param object
     * @param entityClass
     * @return
     */
    private <T> T convertBean(Object object, Class<T> entityClass) {
        if (null == object) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(object), entityClass);
    }

    /**
     * 方法说明：对象转化为Map
     *
     * @param object
     * @return
     */
    public Map<?, ?> objectToMap(Object object) {
        return convertBean(object, Map.class);
    }


    /**
     * 创建集合
     */
    @Test
    public void create() {
        MongoCollection<Document> database = mongoTemplate.createCollection("database");
        System.out.println(database);
    }


    @Test
    public void insert() throws Exception {
        Person person = new Person("李四", 18);
//        Person person1 = mongoTemplate.save(person);
//        System.out.println(person1);
//        Map<?, ?> map = objectToMap(person);
//        Map<String, Object> map = ObjectAndMapUtil2.objectToMap(person);
//        System.out.println(map);
//        System.out.println(map.get("name") instanceof String);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 20);
        map.put("address", "北京");
        Map<String, Object> map1 = mongoTemplate.save(map,"test");
        System.out.println(map1);
    }

    @Test
    public void updateFirst() {
        Query query = new Query(Criteria.where("name").is("李四"));
        Update update = new Update().set("age", 20);
        UpdateResult result = mongoTemplate.updateFirst(query, update, Person.class);
        //updateMulti 更新查询返回结果集的全部
//        mongoTemplate.updateMulti(query,update,Book.class);
        //upsert 更新对象不存在则去添加
//        mongoTemplate.upsert(query,update,Book.class);
        System.out.println(result);
    }

    @Test
    public void updateFirst2() {
        Query query = new Query(Criteria.where("name").is("张三"));
        Update update = new Update().set("age", 20).set("address", "上海");
        UpdateResult result = mongoTemplate.updateFirst(query, update, "test");
        //updateMulti 更新查询返回结果集的全部
//        mongoTemplate.updateMulti(query,update,Book.class);
        //upsert 更新对象不存在则去添加
//        mongoTemplate.upsert(query,update,Book.class);
        System.out.println(result);
    }


    @Test
    public void findOne() {
        Query query = new Query(Criteria.where("name").is("李四"));
        Person person = mongoTemplate.findOne(query, Person.class,"person");
        System.out.println(person);

    }


    @Test
    public void findAll() {
        List<Person> personList = mongoTemplate.findAll(Person.class,"person");
//        System.out.println(personList);
        personList.stream().forEach(System.out::println);
    }

    //删除集合中的符合条件的数据【表中的行】
    @Test
    public void deleteOneInCollection(){
        Query query = new Query().addCriteria(Criteria.where("name").is("张三"));
        DeleteResult person = mongoTemplate.remove(query, "person");
        System.out.println(person);
    }

    @Test
    public void deleteItemOnCollection() {
        Query query = new Query(Criteria.where("name").is("Joe"));
        mongoTemplate.remove(query, "person");
        System.out.println("SUCCESS");
    }


    /**
     * 删除对应集合，【表】
     */
    @Test
    public void dropCollection() {
        mongoOperations.dropCollection("person");
        System.out.println("SUCCESS");
    }

}
