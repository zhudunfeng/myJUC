package com.adun;

import com.adun.dto.ProductDto;
import com.adun.factory1.ProductChainFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:15
 */
@SpringBootTest
public class SimpleChain1Tests {

    @Test
    public void testChain1(){
        ProductDto productDto = new ProductDto();
        ProductChainFactory productChainFactory = new ProductChainFactory();
        productChainFactory.executeHandle(productDto);
    }
}
