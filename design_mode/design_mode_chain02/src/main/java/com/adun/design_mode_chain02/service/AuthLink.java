package com.adun.design_mode_chain02.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ADun
 * @date 2022/8/7 12:21
 */
public abstract class AuthLink {
    protected Logger logger = LoggerFactory.getLogger(AuthLink.class);

    protected SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式
    protected String levelUserId; //级别人员ID
    protected String levelUserName; //级别人员姓名
    private AuthLink next; //责任链

    public AuthLink(String levelUserId, String levelUserName) {
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
    }


    public AuthLink next(){
        return this.next;
    }

    public AuthLink appendNext(AuthLink next){
        this.next=next;
        return this;
//        return this.next;
    }

    /**
     * 这是每一个实现者必须实现的类，不同的审核级别处理不同的业务。
     * @param uId
     * @param orderId
     * @param authDate
     * @return
     */
    public abstract AuthInfo doAuth(String uId, String orderId, Date authDate);
}
