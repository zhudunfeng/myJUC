package com.adun.domain.strategy;

import com.adun.domain.factory.Factory;
import org.springframework.stereotype.Component;

/**
 * @author ADun
 * @date 2023/2/1 0:11
 */
@Component
public class PiQiuHandler implements Handler{
    @Override
    public void AA(String nickName) {
        //业务逻辑
        System.out.println("我是皮球");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register("piqiu", this);
    }
}
