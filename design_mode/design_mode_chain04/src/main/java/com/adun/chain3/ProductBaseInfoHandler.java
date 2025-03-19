package com.adun.chain3;

import com.adun.dto.ProductDto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:08
 */
@Service
@Order(1)
//     @Duty(type=ProductTypeConst.PRODUCT_GENERAL,order=10)
public class ProductBaseInfoHandler extends IHandler<ProductDto, Boolean> {

    @Override
    public Boolean handle(ProductDto t) {
        System.out.println("1");
        // 处理商品基本信息
        return super.handle(t);
    }
}
