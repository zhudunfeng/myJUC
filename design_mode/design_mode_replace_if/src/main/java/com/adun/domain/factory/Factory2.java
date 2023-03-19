package com.adun.domain.factory;

import com.adun.domain.strategy.Handler;
import com.adun.domain.template.AbstractHandler;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂设计模式
 *
 * @author ADun
 * @date 2023/2/1 0:15
 */
public class Factory2 {

    private static Map<String, AbstractHandler> strategyMap = new HashMap<>();


    /**
     * 获取对应的策略类
     *
     * @param key
     * @return
     */
    public static AbstractHandler getInvokeStrategy(String key) {
        return strategyMap.get(key);
    }

    /**
     * 在bean初始化时进行加载
     *
     * @param key     策略对应的key
     * @param handler 策略处理类
     */
    public static void register(String key, AbstractHandler handler) {
        if (StringUtils.isEmpty(key) || null == handler) {
            return;
        }
        strategyMap.put(key, handler);
    }

}
