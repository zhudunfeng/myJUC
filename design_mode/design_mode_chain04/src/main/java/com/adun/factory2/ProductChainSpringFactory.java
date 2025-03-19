package com.adun.factory2;

import com.adun.chain1.IHandler;
import com.adun.dto.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:17
 */
// @Component
public class ProductChainSpringFactory {

    private IHandler first;

    /**
     * 存放系统中责任连具体处理类
     * @param handlerList
     */
    public ProductChainSpringFactory(List<IHandler> handlerList) {
        Assert.notEmpty(handlerList, "无责任实现bean");

        for (int i = 0; i < handlerList.size() - 1; i++) {
            handlerList.get(i).setNextHandler(handlerList.get(i + 1));
        }

        first = handlerList.get(0);
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
