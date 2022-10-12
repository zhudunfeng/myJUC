package com.adun.design_mode_chain02.service.impl;

import com.adun.design_mode_chain02.service.AuthInfo;
import com.adun.design_mode_chain02.service.AuthLink;
import com.adun.design_mode_chain02.service.AuthService;

import java.util.Date;

/**
 * @author ADun
 * @date 2022/8/7 16:03
 */
public class Level1AuthLink extends AuthLink {

    public Level1AuthLink(String levelUserId, String levelUserName) {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        //查询当前用户有没有审批的单子
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            //没有审批过，直接审批
            return new AuthInfo("0001", "单号：", orderId, " 状态：待一级审批负责人 ", levelUserName);
        }

        AuthLink next = super.next();
        if (null == next) {
            //没有下一级审批，直接审批结束
            return new AuthInfo("0000", "单号：", orderId, " 状态：一级审批完成负责人", " 时间：", f.format(date), " 审批人：", levelUserName);
        }

        //调用下一级审批
        return next.doAuth(uId, orderId, authDate);
    }
}
