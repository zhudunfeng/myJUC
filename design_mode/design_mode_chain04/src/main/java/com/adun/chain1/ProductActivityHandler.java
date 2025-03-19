package com.adun.chain1;

import com.adun.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:06
 */
// @Service
// @Order(2)
public class ProductActivityHandler extends IHandler{
    @Override
    public Boolean handle(ProductDto t) {
        System.out.println("2");
        //处理促销信息
        return super.handle(t);
    }
}
