package com.adun.test_MultiThreadTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * 多线程异步处理时的事务管理
 * 1.addFunction 添加要异步执行的方法
 * 2.execute方法中，使⽤全局的计数器和异常标记字段，统计个异步线程执行的结果
 * 当所有异步线程执行完之后，根据异常标记字段判断是回滚还是提交事务。
 *
 * @author ADUN
 */
public class MultiThreadTransactionComponent {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 事务管理器
     */
    private PlatformTransactionManager platformTransactionManager;

    /**
     * 线程池
     */
    private ThreadPoolTaskExecutor threadPoolExecutor;

    /**
     * 有序任务集合
     */
    private ArrayList<Supplier> supplierList = new ArrayList<>(16);

    /**
     * 创建执行计数器
     */
    private CountDownLatch countDownLatch;

    /**
     * 异常标志位
     */
    private AtomicReference<Boolean> isError = new AtomicReference<>(false);

    /**
     * 构造函数
     *
     * @param transactionManager 事务管理器
     * @param threadPoolExecutor 线程池
     */
    public MultiThreadTransactionComponent(PlatformTransactionManager transactionManager, ThreadPoolTaskExecutor threadPoolExecutor) {
        this.platformTransactionManager = transactionManager;
        this.threadPoolExecutor = threadPoolExecutor;
    }

    /**
     * 添加要异步执行的方法程序
     *
     * @param supplier 任务
     */
    public void addFunction(Supplier supplier) {
        //有序任务集合添加任务
        supplierList.add(supplier);
    }

    /**
     * 执⾏队列中的任务
     */
    public void execute() {
        // 初始化线程计数器
        countDownLatch = new CountDownLatch(supplierList.size());
        logger.info("【多线程事务】开始...");
        for (Supplier supplier : supplierList) {
            //线程池提交任务
            this.threadPoolExecutor.submit(new TransactionRunnable(platformTransactionManager, supplier));
        }
        try {
            // 主线程等待计数器为 0 时进⾏提交或回滚
            countDownLatch.await();
            if (isError.get()) {
                logger.error("【多线程事务】多线程执行失败,事务已回滚");
                // 主线程抛出⾃定义的异常
                throw new RuntimeException("多线程执行失败");
            }
            logger.info("【多线程事务】多线程执行完成,事务已提交");
        } catch (InterruptedException e) {
            logger.error("多线程执行失败");
            // 主线程抛出⾃定义的异常
            throw new RuntimeException("多线程执行失败" + e.getMessage());
        }
    }

    /**
     * 实现 Runnable 接口
     */
    class TransactionRunnable implements Runnable {
        /**
         * 事务管理器
         */
        private PlatformTransactionManager platformTransactionManager;

        /**
         * 当前任务
         */
        private Supplier supplier;

        /**
         * 构造函数
         *
         * @param platformTransactionManager 事务管理器
         * @param supplier                   当前任务
         */
        public TransactionRunnable(PlatformTransactionManager platformTransactionManager, Supplier supplier) {
            this.platformTransactionManager = platformTransactionManager;
            this.supplier = supplier;
        }

        @Override
        public void run() {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            // 创建当前线任务的事务
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus transactionStatus = this.platformTransactionManager.getTransaction(def);
            try {
                // 尝试获取任务值
                this.supplier.get();
            } catch (Exception e) {
                // 异常时，设置错误标记
                isError.set(true);
                logger.error("【多线程事务】执⾏失败{}", e.getMessage());
            }
            // 线程计数器 -1
            countDownLatch.countDown();
            try {
                // 子线程等待计数器为 0 时进⾏提交或回滚,两阶段提交
                countDownLatch.await();
                if (isError.get()) {
                    logger.info("【多线程事务-子线程】事务回滚");
                    //事务回滚
                    platformTransactionManager.rollback(transactionStatus);
                } else {
                    logger.info("【多线程事务-子线程】事务提交");
                    //事务提交
                    platformTransactionManager.commit(transactionStatus);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

