package com.adun.factory1;

import com.adun.chain1.IHandler;
import com.adun.chain1.ProductActivityHandler;
import com.adun.chain1.ProductBaseInfoHandler;
import com.adun.dto.ProductDto;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:11
 */
public class ProductChainFactory {

    IHandler first;

    /**
     * 存放系统中责任链具体处理类
     */
    public ProductChainFactory() {
        ProductBaseInfoHandler productBaseInfoHandler = new ProductBaseInfoHandler();
        ProductActivityHandler productActivityHandler = new ProductActivityHandler();
        productBaseInfoHandler.setNextHandler(productActivityHandler);

        first = productBaseInfoHandler;
    }

    /**
     * 指定具体业务场景中的责任链集合
     * @param productDto
     * @return
     */
    public Boolean executeHandle(ProductDto productDto){
        return first.handle(productDto);
    }
}
