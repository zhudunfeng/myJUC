package com.adun;

import com.adun.chain1.IHandler;
import com.adun.dto.ProductDto;
import com.adun.factory3.ProductChainSpringFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:15
 */
@SpringBootTest
public class SpringChain3Tests {

    @Resource
    private ProductChainSpringFactory<ProductDto,Boolean> factory;

    @Resource
    private ProductChainSpringFactory<Object,Boolean> adunProductChainSpringFactory;

    @Test
    public void testChain3(){
        ProductDto productDto = new ProductDto();
        factory.executeHandle(productDto);
    }

    @Test
    public void testChain31(){
        ProductDto productDto = new ProductDto();
        adunProductChainSpringFactory.executeHandle(productDto);
    }
}
