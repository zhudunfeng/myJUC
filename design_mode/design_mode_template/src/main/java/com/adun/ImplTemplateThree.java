package com.adun;

/**
 * @author ADun
 * @date 2022/9/13 22:25
 */
public class ImplTemplateThree extends AbstractTemplate {
    @Override
    public void templateMethodOne() {
        System.out.println("实现模板方案三（方法A）");
    }

    @Override
    public void templateMethodTwo() {
        System.out.println("实现模板方案三（方法B）");
    }

    @Override
    public void templateMethodThree() {
        System.out.println("实现模板方案三（方法C）");
    }
}
