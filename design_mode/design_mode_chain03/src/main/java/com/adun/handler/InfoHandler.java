package com.adun.handler;


import com.adun.bean.Programmer;
import com.adun.chain.Handler;

/**
 * 校验活动细节
 */
public class InfoHandler extends Handler {

    @Override
    public boolean doHandler(Programmer programmer) {

        if(!programmer.getInfo().contains("扫描文末二维码，关注公众号哪吒编程，定期分享Java干货，还有不定期的送书活动，包邮到你家")){
            return false;
        }

        if(null == next){
            return true;
        }

        return next.doHandler(programmer);
    }
}
