package com.adun.controller;

import com.adun.common.utils.ReqDedupHelper;
import com.adun.entities.TestModelVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zhu Dunfeng
 * @date 2022/1/7 14:45
 */
@RequestMapping("/test")
@RestController
public class TestController {

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private void set(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @RequestMapping("/test1")
    public void test(String reqJson) {
        String userId = "12345678";//用户
        String method = "pay";//接口名
        String dedupMD5 = new ReqDedupHelper().dedupParamMD5(reqJson, "requestTime");//计算请求参数摘要，其中剔除里面请求时间的干扰
        String KEY = "dedup:U=" + userId + "M=" + method + "P=" + dedupMD5;

        long expireTime = 1000;// 1000毫秒过期，1000ms内的重复请求会认为重复
        long expireAt = System.currentTimeMillis() + expireTime;
        String val = "expireAt@" + expireAt;

        // NOTE:直接SETNX不支持带过期时间，所以设置+过期不是原子操作，极端情况下可能设置了就不过期了，后面相同请求可能会误以为需要去重，所以这里使用底层API，保证SETNX+过期时间是原子操作
        Boolean firstSet = stringRedisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.set(KEY.getBytes(), val.getBytes(), Expiration.milliseconds(expireTime)
                        , RedisStringCommands.SetOption.SET_IF_ABSENT));

        final boolean isConsiderDup;
        if (firstSet != null && firstSet) {
            isConsiderDup = false;
        } else {
            isConsiderDup = true;
        }
    }


    @RequestMapping("/list")
    public String listTest(@RequestBody List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return "OK";
    }

    @RequestMapping("/insert")
    public String insertTest(@RequestBody TestModelVO testModelVO) {
        System.out.println(testModelVO);
        System.out.println(testModelVO.getArray());
        return "OK";
    }

    @PostMapping("/edit")
    public String updateFormValue(@RequestBody String formValue) {
        System.out.println(formValue);
        return formValue;
    }

    @PostMapping("/obj")
    public String objTest(@RequestBody Object obj) {
        System.out.println("普通toString：" + obj.toString());
        System.out.println("JSON转换:" + JSON.toJSONString(obj));
        return obj.toString();
    }


}
