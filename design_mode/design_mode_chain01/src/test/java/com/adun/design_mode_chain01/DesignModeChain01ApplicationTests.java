package com.adun.design_mode_chain01;

import com.adun.design_mode_chain01.controller.AuthController;
import com.adun.design_mode_chain01.service.AuthService;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;

@SpringBootTest
class DesignModeChain01ApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(DesignModeChain01ApplicationTests.class);

    @Test
    void contextLoads() {
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
