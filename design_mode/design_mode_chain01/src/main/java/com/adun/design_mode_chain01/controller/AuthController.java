package com.adun.design_mode_chain01.controller;

import com.adun.design_mode_chain01.entites.AuthInfo;
import com.adun.design_mode_chain01.service.AuthService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ADun
 * @date 2022/8/7 12:07
 */
public class AuthController {
    private SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 时间格式化

    public AuthInfo doAuth(String uId, String orderId, Date authDate) throws ParseException {
        //三级审批
        Date date = AuthService.queryAuthInfo("1000013", orderId);
        if (date == null) {
            return new AuthInfo("0001", "单号", orderId, " 状态：待三级审批负责人 ", "王工");
        }

        //二级审批
        if (authDate.after(f.parse("2022-08-07 00:00:00")) && authDate.before(f.parse("2020-08-07 23:59:59"))) {
            date = AuthService.queryAuthInfo("1000012", orderId);
            if (date == null) {
                return new AuthInfo("0001", "单号", orderId, " 状态：待二级审批负责人 ", "张经理");
            }
        }

        //一级审批
        if (authDate.after(f.parse("2022-08-07 00:00:00")) && authDate.before(f.parse("2020-08-07 23:59:59"))) {
            date = AuthService.queryAuthInfo("1000011", orderId);
            if (date == null) {
                return new AuthInfo("0001", "单号", orderId, " 状态：待一级审批负责人 ", "段总");
            }
        }


        return new AuthInfo("0001", "单号", orderId, "状态:完成状态");
    }
}
