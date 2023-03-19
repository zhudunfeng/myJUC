package com.adun.domain.template;

import com.adun.domain.factory.Factory;
import org.springframework.stereotype.Component;

/**
 * @author ADun
 * @date 2023/2/1 0:10
 */
@Component
public class WoNiuHandler2 extends AbstractHandler2 {
    @Override
    public void AA(String nickName) {
        //业务逻辑
        System.out.println("我是蜗牛2");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register("woniu2", this);
    }
}
