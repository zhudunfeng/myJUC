package com.adun.chain3;

import com.adun.dto.ProductDto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:06
 */
@Service
@Order(2)
public class ProductActivityHandler extends IHandler<ProductDto, Boolean> {
    @Override
    public Boolean handle(ProductDto t) {
        System.out.println("2");
        // 处理促销信息
        return super.handle(t);
    }
}
