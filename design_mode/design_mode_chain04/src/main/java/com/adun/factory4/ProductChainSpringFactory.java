package com.adun.factory4;

import com.adun.annotations.Duty;
import com.adun.chain3.IHandler;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:42
 */
public class ProductChainSpringFactory<T,R> {

    /**
     * 存放责任链路上的具体处理类
     * k- 具体业务枚举
     * v-具体业务场景下的责任链路集合
     */
    private Map<String, List<IHandler<T,R>>> handleMap;

    /**
     * 存放系统中责任连具体处理类
     *
     * @param handlerList
     */
    public ProductChainSpringFactory(List<IHandler<T,R>> handlerList) {
        handleMap =handlerList.stream()
                .sorted(Comparator.comparingInt(h -> {
                    Order order = AnnotationUtils.findAnnotation(h.getClass(), Order.class);
                    return order.value();
                }))
                // 将duty.type[] 形成map<type,handler>
                .flatMap(handler -> {
                    Duty duty = AnnotationUtils.findAnnotation(handler.getClass(), Duty.class);
                    if (duty != null && duty.type() != null) {
                        return Arrays.stream(duty.type())
                                .map(type-> new AbstractMap.SimpleEntry<>(type, handler));
                    }else {
                        return Stream.empty();
                    }
                })
                .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey,
                        Collectors.mapping(AbstractMap.SimpleEntry::getValue, Collectors.toList())));
    }

    public R executeHandle(T t, String type) {
        List<IHandler<T, R>> handlerList = handleMap.get(type);
        for (int i = 0; i < handlerList.size() - 1; i++) {
            handlerList.get(i).setNextHandler(handlerList.get(i + 1));
        }
        return handlerList.get(0).handle(t);
    }
}
