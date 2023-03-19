package com.adun;

import com.adun.domain.factory.Factory;
import com.adun.domain.strategy.Handler;
import com.adun.domain.strategy.PiQiuHandler;
import com.adun.domain.strategy.WoNiuHandler;

/**
 * @author ADun
 * @date 2023/2/1 0:03
 */
public class Demo {
    public static void main(String[] args) {
        String nickName = "蜗牛";

        if ("皮球".equals(nickName)) {
            //业务逻辑
            //System.out.println("我是皮球");
            new PiQiuHandler().AA(nickName);
        }else if("蜗牛".equals(nickName)){
            //业务逻辑
            //System.out.println("我是蜗牛");
            new WoNiuHandler().AA(nickName);
        }else if("牛蛙".equals(nickName)){
            //业务逻辑
            System.out.println("我是牛蛙");
        }



    }
}
