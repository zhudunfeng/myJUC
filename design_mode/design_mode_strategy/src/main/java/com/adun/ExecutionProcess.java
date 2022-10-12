package com.adun;

/**
 * 执行流程
 *
 * @author ADun
 * @date 2022/9/13 22:45
 */
public class ExecutionProcess {

    public void executeMethod(Strategy strategy){
        //执行流程
        System.out.println("执行流程");
        //执行流程
        System.out.println("执行流程");
        //执行流程
        System.out.println("执行流程");
        //执行流程
        System.out.println("执行流程");


        //需要一个策略，或者一个算法，情况不一致【达到同一种目的】
        strategy.strategyMethod();

        //执行流程
        System.out.println("执行流程");
    }

}
