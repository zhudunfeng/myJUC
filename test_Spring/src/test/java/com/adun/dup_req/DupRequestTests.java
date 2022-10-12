package com.adun.dup_req;

import com.adun.common.utils.ReqDedupHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author ADun
 * @date 2022/8/22 23:32
 */
@SpringBootTest
public class DupRequestTests {


    private StringRedisTemplate stringRedisTemplate;




    @Autowired
    private void set(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Test
    public void test() {
        //两个请求一样，但是请求时间差一秒
        String reqJson = "{\n" +
                "\"requestTime\" :\"20190101120001\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";

        String reqJson2 = "{\n" +
                "\"requestTime\" :\"20190101120002\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";


        String userId = "12345678";//用户
        String method = "pay";//接口名
        String dedupMD5 = new ReqDedupHelper().dedupParamMD5(reqJson, "requestTime");//计算请求参数摘要，其中剔除里面请求时间的干扰
        String KEY = "dedup:U=" + userId + "M=" + method + "P=" + dedupMD5;

        long expireTime = 20000;// 1000毫秒过期，1000ms内的重复请求会认为重复
        long expireAt = System.currentTimeMillis() + expireTime;
        String val = "expireAt@" + expireAt;

        //redis key还存在的话要就认为请求是重复的
        // NOTE:直接SETNX不支持带过期时间，所以设置+过期不是原子操作，极端情况下可能设置了就不过期了，后面相同请求可能会误以为需要去重，所以这里使用底层API，保证SETNX+过期时间是原子操作
        Boolean firstSet = stringRedisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.set(KEY.getBytes(), val.getBytes(), Expiration.milliseconds(expireTime),
                        RedisStringCommands.SetOption.SET_IF_ABSENT));

        final boolean isConsiderDup;
        if (firstSet != null && firstSet) {
            // 第一次访问
            isConsiderDup = false;
        } else {
            // redis值已存在，认为是重复了
            isConsiderDup = true;
        }

        System.out.println(isConsiderDup);
    }


    @Test
    public void testReqMd5() {
        //两个请求一样，但是请求时间差一秒
        String req = "{\n" +
                "\"requestTime\" :\"20190101120001\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";

        String req2 = "{\n" +
                "\"requestTime\" :\"20190101120002\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";

        //全参数比对，所以两个参数MD5不同
        String dedupMD5 = new ReqDedupHelper().dedupParamMD5(req);
        String dedupMD52 = new ReqDedupHelper().dedupParamMD5(req2);
        System.out.println("req1MD5 = " + dedupMD5 + " , req2MD5=" + dedupMD52);

        //去除时间参数比对，MD5相同
        String dedupMD53 = new ReqDedupHelper().dedupParamMD5(req, "requestTime");
        String dedupMD54 = new ReqDedupHelper().dedupParamMD5(req2, "requestTime");
        System.out.println("req1MD5 = " + dedupMD53 + " , req2MD5=" + dedupMD54);
    }

}
