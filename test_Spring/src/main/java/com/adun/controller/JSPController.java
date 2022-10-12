package com.adun.controller;

import com.adun.log.LogContainer;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ADun
 * @date 2022/8/5 12:50
 */


@Controller
//@RequestMapping("/")
public class JSPController {
    public static final Logger logger = LoggerFactory.getLogger(JSPController.class);
    @GetMapping("/index")
    public String toIndex(){
        logger.info("哈哈哈哈11111");
        LogContainer.log("调用1次Index");
        return "index";
    }

    @GetMapping("/fileUpload")
    public String toFileUpload(HttpServletResponse response){
        response.setContentType("text/html;character=utf-8");
        return "fileUpload";
    }
}
