package com.adun;

/**
 * @author ADun
 * @date 2022/9/13 22:42
 */
public class ImplStrategyTwo implements Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("按照策略二的算法执行");
    }
}
