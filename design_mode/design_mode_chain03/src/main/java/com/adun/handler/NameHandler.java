package com.adun.handler;


import com.adun.bean.Programmer;
import com.adun.chain.Handler;

/**
 * 校验名字
 */
public class NameHandler extends Handler {

    @Override
    public boolean doHandler(Programmer programmer) {

        if(!"哪吒编程".equals(programmer.getName())){
            return false;
        }

        if(null == next){
            return true;
        }

        return next.doHandler(programmer);
    }
}
