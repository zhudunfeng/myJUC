package com.adun;

import com.adun.dto.ProductDto;
import com.adun.factory1.ProductChainFactory;
import com.adun.factory2.ProductChainSpringFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:15
 */
@SpringBootTest
public class SpringChain2Tests {

    @Resource
    private ProductChainSpringFactory factory;
    @Test
    public void testChain2(){
        ProductDto productDto = new ProductDto();
        factory.executeHandle(productDto);
    }
}
