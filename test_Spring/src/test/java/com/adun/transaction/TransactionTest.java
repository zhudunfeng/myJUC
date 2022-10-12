package com.adun.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zhu Dunfeng
 * @date 2022/5/9 22:08
 */
@SpringBootTest
public class TransactionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Test
    public void insert(){
        jdbcTemplate.execute("insert into user (name,age) values ('aa',18)");
        int num=10/0;
    }
}
