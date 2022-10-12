package com.adun.handler;

import com.adun.bean.Programmer;
import com.adun.chain.Handler;

/**
 * 校验项目名称
 */
public class ProjectHandler extends Handler {
    @Override
    public boolean doHandler(Programmer programmer) {
        if(!"公众号".equals(programmer.getProject())){
            return false;
        }

        if(null == next){
            return true;
        }
        return next.doHandler(programmer);
    }
}
