package com.adun.chain3;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:25
 */
@Order(2)
@Service
public class ADunHandler extends IHandler<Object,Boolean>{

    @Override
    public Boolean handle(Object o) {
        System.out.println("ADUN");
        return super.handle(o);
    }
}
