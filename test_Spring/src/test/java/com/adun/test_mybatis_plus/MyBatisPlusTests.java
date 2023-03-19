package com.adun.test_mybatis_plus;

import com.adun.entities.User;
import com.adun.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ADun
 * @date 2023/2/12 15:05
 */
@SpringBootTest
public class MyBatisPlusTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test_user(){
        final User adun = new User("adun", 18);
        userMapper.insert(adun);
    }

    @Test
    public void  test_get_user(){
        final LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getId, 1);
        final User user = userMapper.selectOne(userLambdaQueryWrapper);
        System.out.println(user);
    }

    @Test
    public void testCustomWapper(){
        final LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,1);
        User user = userMapper.getUserByCustomWapper(lambdaQueryWrapper);
        System.out.println(user);
    }
}
