package com.adun.domain.strategy;

import com.adun.domain.factory.Factory;
import org.springframework.stereotype.Component;

/**
 * @author ADun
 * @date 2023/2/1 0:10
 */
@Component
public class WoNiuHandler implements Handler {
    @Override
    public void AA(String nickName) {
        //业务逻辑
        System.out.println("我是蜗牛");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register("woniu", this);
    }
}
