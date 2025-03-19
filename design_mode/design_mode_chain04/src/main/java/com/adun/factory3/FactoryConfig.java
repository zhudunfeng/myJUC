package com.adun.factory3;

import com.adun.chain3.IHandler;
import com.adun.dto.ProductDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Objects;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:33
 */
@Configuration
public class FactoryConfig {

    @Bean
    public ProductChainSpringFactory<ProductDto,Boolean> productChainSpringFactory(List<IHandler<ProductDto,Boolean>> handlerList){
        return new ProductChainSpringFactory<>(handlerList);
    }

    @Bean
    public ProductChainSpringFactory<Object,Boolean> adunProductChainSpringFactory(List<IHandler<Object,Boolean>> handlerList){
        return new ProductChainSpringFactory<>(handlerList);
    }
}
