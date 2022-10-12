package com.adun.design_mode_chain02.service.impl;

import com.adun.design_mode_chain02.service.AuthInfo;
import com.adun.design_mode_chain02.service.AuthLink;
import com.adun.design_mode_chain02.service.AuthService;

import java.text.ParseException;
import java.util.Date;

/**
 * @author ADun
 * @date 2022/8/7 16:12
 */
public class Level2AuthLink extends AuthLink {

    private Date beginDate = f.parse("2022-08-07 00:00:00");
    private Date endDate = f.parse("2022-08-20 23:59:59");

    public Level2AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待二级审批负责人 ", levelUserName);
        }
        AuthLink next = super.next();
        if (null == next) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：二级审批完成负责人", " 时间：", f.format(date), " 审批人：", levelUserName);
        }

        //在时间内，审批完成
        if (authDate.before(beginDate) || authDate.after(endDate)) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：二级审批完成负责人", " 时间：", f.format(date), " 审批人：", levelUserName);
        }

        //责任链
        return next.doAuth(uId, orderId, authDate);
    }
}
