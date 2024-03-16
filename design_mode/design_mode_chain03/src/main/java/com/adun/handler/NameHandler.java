package com.adun.handler;


import com.adun.bean.Programmer;
import com.adun.chain.Handler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 校验名字
 */
@Order(2)
@Component
public class NameHandler extends Handler {

    @Override
    public boolean doHandler(Programmer programmer) {

        if (!"哪吒编程".equals(programmer.getName())) {
            return false;
        }
        System.out.println(programmer.getName());

        if (null == next) {
            return true;
        }

        return next.doHandler(programmer);
    }
}
