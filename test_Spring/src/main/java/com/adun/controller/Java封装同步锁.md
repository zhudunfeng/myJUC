> 要求：
>
> （1）同一个订单才加一个同步锁
> （2）不同的订单之间不需要同步，可以并行



# 初始版本

```java
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
     * 方式一：synchnronized (this) 锁住的是当前对象SyncLockController实例，
     * 001与002共同使用当前对象
     *
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

    /**
     * 方式二：orderId.intern() 是将orderId放入静态池，所有当前的orderId共同使用一个字符串对象
     */
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
}
```



# 翻车版本V1

```java
@Component
public class SynchronizedByKey {

    //翻车1
   Map<String,Object> mutexCache = new ConcurrentHashMap();

    public void exec(String key,Runnable statement){

     Object mutexKey = mutexCache.computeIfAbsent(key, k -> new Object());

        synchronized (mutexKey){
            try {
                statement.run();
            } finally {
                mutexCache.remove(key);
            }
        }

    }


}
```

翻车原因：

# 翻车版本V2

```java
@Component
public class SynchronizedByKey {

   /**
     * 翻车2
     */
    Map<String, ReentrantLock> mutexCache = new ConcurrentHashMap();

    public void exec(String key, Runnable statement) {

        /**
         * 锁获取的三种情况：
         * (1)获取到自己创建的
         * (2)获取到别人创建的（正常的、有排队线程的）
         * (3)拿到一把刚刚被remove的锁
         */
        
        //翻车原因：极端情况下，新进来的线程在这里会拿到刚刚mutexCache.remove(key)的锁
        ReentrantLock mutex4Key = mutex4Key = mutexCache.computeIfAbsent(key, k -> new ReentrantLock());;

        mutex4Key.lock();
        try {
            statement.run();
        } finally {
            if (mutex4Key.getQueueLength() == 0) {
                mutexCache.remove(key);
            }
            mutex4Key.unlock();
        }
    }


}
```



# 最终版本

```java
@Component
public class SynchronizedByKey {

    Map<String, ReentrantLock> mutexCache = new ConcurrentHashMap();

    public void exec(String key, Runnable statement) {

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
            statement.run();
        } finally {
            if (mutex4Key.getQueueLength() == 0) {
                mutexCache.remove(key);
            }
            mutex4Key.unlock();
        }
    }
}
```

