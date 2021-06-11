package com.adun.mongo;

import com.adun.test_mongodb.expand.Person;
import com.mongodb.client.MongoCollection;
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

import java.util.List;

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


    @Test
    public void create(){
        MongoCollection<Document> database = mongoTemplate.createCollection("database");
        System.out.println(database);
    }


    @Test
    public void insert(){
        Person person = new Person("李四", 18);
        Person person1 = mongoTemplate.save(person);
        System.out.println(person1);

    }

    @Test
    public void updateFirst(){
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
    public void findOne(){
        Query query = new Query(Criteria.where("name").is("李四"));
        Person person = mongoTemplate.findOne(query, Person.class);
        System.out.println(person);

    }


    @Test
    public void findAll(){
        List<Person> personList = mongoTemplate.findAll(Person.class);
//        System.out.println(personList);
        personList.stream().forEach(System.out::println);
    }

    @Test
    public void dropCollection(){
        mongoOperations.dropCollection("person");
        System.out.println("SUCCESS");
    }

    @Test
    public void deleteDatabase(){
        Query query = new Query(Criteria.where("name").is("Joe"));
        mongoTemplate.remove(query,"person");
        System.out.println("SUCCESS");
    }




}
