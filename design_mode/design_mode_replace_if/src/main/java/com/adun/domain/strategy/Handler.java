package com.adun.domain.strategy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 策略设计模式
 * @author ADun
 * @date 2023/2/1 0:07
 */
public interface Handler extends InitializingBean {
    void AA(String nickName);
}
