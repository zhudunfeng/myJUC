package com.adun.aop.aspect;

import com.adun.aop.annotation.SyncLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Zhu Dunfeng
 * @date 2022/3/2 21:56
 */
@Aspect
@Component
public class SyncLockHandler {

    Map<String, ReentrantLock> mutexCache = new ConcurrentHashMap();

    @Pointcut("@annotation(com.adun.aop.annotation.SyncLock)")
    public void pointCut(){};

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        boolean annotationPresent = method.isAnnotationPresent(SyncLock.class);
        if (annotationPresent) {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                String key= arg instanceof String ? ((String) arg) : String.valueOf(arg);
                /**
                 * 锁获取的三种情况：
                 * (1)获取到自己创建的
                 * (2)获取到别人创建的（正常的、有排队线程的）
                 * (3)拿到一把刚刚被remove的锁
                 */
                ReentrantLock mutex4Key = null;
                ReentrantLock mutexInCache = null;

                do {
                    if(mutex4Key!=null){
                        mutex4Key.unlock();
                    }
                    mutex4Key = mutexCache.computeIfAbsent(key, k -> new ReentrantLock());
                    mutex4Key.lock();

                    mutexInCache = mutexCache.get(key);
                    /**
                     * 双重检查
                     *  1.mutexInCache==null 获取到的锁被移除掉了
                     *  2.mutex4Key!=mutexInCache 和缓存中不一致了  lock后锁被删除，新进来的线程又创建了新锁
                     */
                } while (mutexInCache == null || mutex4Key != mutexInCache);

                try {
                   joinPoint.proceed();
                } finally {
                    if (mutex4Key.getQueueLength() == 0) {
                        mutexCache.remove(key);
                    }
                    mutex4Key.unlock();
                }
            }
        }

    }



}
