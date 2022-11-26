package com.adun;

/**
 * 策略模式测试模型
 *
 * @author ADun
 * @date 2022/9/13 22:43
 */
public class TestStrategyPattern {

    public static void main(String[] args) {
        //控制执行流程的类
        ExecutionProcess executionProcess = new ExecutionProcess();
        //提供一种策略（多态的效果，一个具体策略的实现）
        ImplStrategyOne implStrategyOne = new ImplStrategyOne();
        //策略作为参数传递到执行流程内
        executionProcess.executeMethod(implStrategyOne);
    }
}
