package com.adun.factory3;

import com.adun.chain3.IHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 需要指定反省，否则自动装配会装配所有Handler
 *
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:17
 */
// 不能使用此种方式注入，不能指定类的具体泛型
// @Component
public class ProductChainSpringFactory<T, R> {

    private IHandler<T, R> first;

    /**
     * 存放系统中责任连具体处理类
     *
     * @param handlerList
     */
    public ProductChainSpringFactory(List<IHandler<T, R>> handlerList) {
        Assert.notEmpty(handlerList, "无责任实现bean");

        for (int i = 0; i < handlerList.size() - 1; i++) {
            handlerList.get(i).setNextHandler(handlerList.get(i + 1));
        }

        first = handlerList.get(0);
    }

    /**
     * 指定具体业务场景中的责任链集合
     *
     * @param productDto
     * @return
     */
    public R executeHandle(T t) {
        return first.handle(t);
    }
}
