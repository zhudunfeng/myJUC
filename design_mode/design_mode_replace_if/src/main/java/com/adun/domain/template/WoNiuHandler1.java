package com.adun.domain.template;

import com.adun.domain.factory.Factory2;
import org.springframework.stereotype.Component;

/**
 * @author ADun
 * @date 2023/2/1 0:10
 */
@Component
public class WoNiuHandler1 extends AbstractHandler {
    @Override
    public void AA(String nickName) {
        //业务逻辑
        System.out.println("我是蜗牛1");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Factory2.register("woniu1", this);
    }
}
