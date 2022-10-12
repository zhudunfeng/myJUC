package com.adun.aop.annotation;

import java.lang.annotation.*;

/**
 * @author Zhu Dunfeng
 * @date 2022/3/2
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SyncLock {

}
