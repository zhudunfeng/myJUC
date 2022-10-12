package com.adun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/send1")
    public String send1(String message) {
        redisTemplate.convertAndSend("TextChannel", message);
        return "send1 success";
    }

    @GetMapping(value = "send2")
    public String send2(String message) {
        redisTemplate.convertAndSend("kafkaChannel", message);
        return "send2 success";
    }
}
