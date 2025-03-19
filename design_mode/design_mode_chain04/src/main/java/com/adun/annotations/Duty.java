package com.adun.annotations;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:47
 */
@Target({ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Duty {

    /**
     * 标记具体业务场景
     * @return
     */
    String[] type();

    /**
     * 排序: 数值越小，排序越前
     * @return
     */
    int order() default 0;
}
