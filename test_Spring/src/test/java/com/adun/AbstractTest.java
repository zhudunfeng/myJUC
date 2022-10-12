package com.adun;

import com.adun.test_abstract.OneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author zhudunfeng
 * @date 2021/6/10 13:52
 */
@SpringBootTest
public class AbstractTest {

    @Autowired
    private OneService oneService;



    @Test
    public void print(){
        oneService.print();
    }

    @Test
    public void printOne(){
        oneService.printOne();
    }

    public OneService interfaceM(){
        return new OneService() {
            @Override
            public void print() {
                System.out.println("111111111");
            }
        };
    }

    @Test
    public void printOneWith(){
        interfaceM().print();
    }

}
