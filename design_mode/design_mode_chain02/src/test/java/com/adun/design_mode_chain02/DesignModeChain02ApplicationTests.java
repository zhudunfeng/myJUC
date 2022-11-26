package com.adun.design_mode_chain02;

import com.adun.design_mode_chain02.controller.AuthController;
import com.adun.design_mode_chain02.service.AuthLink;
import com.adun.design_mode_chain02.service.AuthService;
import com.adun.design_mode_chain02.service.impl.Level1AuthLink;
import com.adun.design_mode_chain02.service.impl.Level2AuthLink;
import com.adun.design_mode_chain02.service.impl.Level3AuthLink;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;

@SpringBootTest
class DesignModeChain02ApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(DesignModeChain02ApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    public void test_AuthController1() throws ParseException {
        AuthLink authLink = new Level3AuthLink("1000013", "王工")
                .appendNext(new Level2AuthLink("1000012", "经理")
                        .appendNext(new Level1AuthLink("1000011", "段总")));


        //logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥", "1000998004813441", new Date())));


        // 模拟三级负责人审批
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥", "1000998004813441", new Date())));
        logger.info("测试结果：{}", "模拟三级负责人审批，王工");
        AuthService.auth("1000013", "1000998004813441");


        // 模拟二级负责人审批
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥", "1000998004813441", new Date())));
        logger.info("测试结果：{}", "模拟二级负责人审批，张经理");
        AuthService.auth("1000012", "1000998004813441");

        // 模拟一级负责人审批
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥", "1000998004813441", new Date())));
        logger.info("测试结果：{}", "模拟一级负责人审批，段总");
        AuthService.auth("1000011", "1000998004813441");

        logger.info("测试结果：{}", "审批完成");
    }

    @Test
    public void test_AuthController() throws ParseException {
        AuthController authController = new AuthController();

        // 模拟三级负责人审批
        logger.info("测试结果：{}", JSON.toJSONString(authController.doAuth("小傅哥", "1000998004813441", new Date())));
        logger.info("测试结果：{}", "模拟三级负责人审批，王工");
        AuthService.auth("1000013", "1000998004813441");


        // 模拟二级负责人审批
        logger.info("测试结果：{}", JSON.toJSONString(authController.doAuth("小傅哥", "1000998004813441", new Date())));
        logger.info("测试结果：{}", "模拟二级负责人审批，张经理");
        AuthService.auth("1000012", "1000998004813441");

        // 模拟一级负责人审批
        logger.info("测试结果：{}", JSON.toJSONString(authController.doAuth("小傅哥", "1000998004813441", new Date())));
        logger.info("测试结果：{}", "模拟一级负责人审批，段总");
        AuthService.auth("1000011", "1000998004813441");

        logger.info("测试结果：{}", "审批完成");
    }

}
