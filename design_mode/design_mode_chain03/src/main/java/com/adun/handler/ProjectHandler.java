package com.adun.handler;

import com.adun.bean.Programmer;
import com.adun.chain.Handler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 校验项目名称
 */
@Order(1)
@Component
public class ProjectHandler extends Handler {
    @Override
    public boolean doHandler(Programmer programmer) {
        if (!"公众号".equals(programmer.getProject())) {
            return false;
        }
        System.out.println(programmer.getProject());

        if (null == next) {
            return true;
        }
        return next.doHandler(programmer);
    }
}
