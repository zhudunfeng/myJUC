package com.adun.controller;

import com.adun.aop.annotation.SyncLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

/**
 * @author Zhu Dunfeng
 * @date 2022/3/2 18:27
 *
 *    要求：同一个订单才加一个同步锁
 *         不同的订单之间不需要同步，可以并行
 */
@RequestMapping(value ="/order")
@Controller
public class SyncLockController {

    Logger logger = LoggerFactory.getLogger(SyncLockController.class);

    public void service(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/process1/{orderId}")
    public Map<String, Object> process1(@PathVariable("orderId") String orderId){

        //此时001和002不能并行
        //001结束后002才能开始
        synchronized (this){
            logger.debug("[{}]开始",orderId);
            service();//<-sleep 1.5s
            logger.debug("[{}]结束",orderId);
        }

        return Collections.singletonMap("code", 200);
    }

    @ResponseBody
    @RequestMapping(value ="/process2/{orderId}")
    public Map<String, Object> process2(@PathVariable("orderId") String orderId){

        //此时001和002能够并行，但不推荐这种方式，此时使用synchronized可能会膨胀成重量级锁
        //001和002同时开始
        synchronized (orderId.intern()){
            logger.debug("[{}]开始",orderId);
            service();//<-sleep 1.5s
            logger.debug("[{}]结束",orderId);
        }

        return Collections.singletonMap("code", 200);
    }

    @Autowired
    private SynchronizedByKey synchronizedByKey;

    @ResponseBody
    @RequestMapping(value ="/process3/{orderId}")
    public Map<String, Object> process3(@PathVariable("orderId") String orderId){

        //此时001和002能够并行，但不推荐这种方式，此时使用synchronized可能会膨胀成重量级锁
        //001和002同时开始
        synchronizedByKey.exec(orderId, ()->{
            logger.debug("[{}]开始",orderId);
            service();//<-sleep 1.5s
            logger.debug("[{}]结束",orderId);
        });
        return Collections.singletonMap("code", 200);
    }

    @SyncLock
    @ResponseBody
    @RequestMapping(value ="/process4/{orderId}")
    public Map<String, Object> process4(@PathVariable("orderId") String orderId){

        //此时001和002能够并行，但不推荐这种方式，此时使用synchronized可能会膨胀成重量级锁
        //001和002同时开始
//        logger.debug("[{}]开始",orderId);
        //service();//<-sleep 1.5s
//        logger.debug("[{}]结束",orderId);
        System.out.println("开始");
        System.out.println("结束");
        return Collections.singletonMap("code", 200);
    }



}
