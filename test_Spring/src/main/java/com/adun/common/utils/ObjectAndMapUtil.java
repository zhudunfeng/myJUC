package com.adun.common.utils;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class ObjectAndMapUtil {
    private ObjectAndMapUtil() {}

    // map 转 java 对象
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz)
            throws Exception {
        if (map == null) {
            return null;
        }
        T t = clazz.newInstance();
        BeanUtils.populate(t, map);
        return t;
    }

    // java 对象转 map
    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) return null;
        return new BeanMap(obj);
    }
}
