package com.adun.test_abstract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.TextScore;

/**
 * @author zhudunfeng
 * @date 2021/6/10 13:47
 */
public abstract class AbstractTest {

    @Autowired
    private MyService myService;

    public void print(){
        myService.print();
    }

}
