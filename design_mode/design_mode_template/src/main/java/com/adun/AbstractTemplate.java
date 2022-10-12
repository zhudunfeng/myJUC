package com.adun;

/**
 * 一个抽象模板
 * @author ADun
 * @date 2022/9/13 22:21
 */
public abstract class AbstractTemplate {

//    protected AbstractTemplate() {
//        this.templateSequence();
//    }

    {
        this.templateSequence();
    }

    //规定模板方法的执行顺序
    public void templateSequence(){
        this.templateMethodOne();
        this.templateMethodTwo();
        this.templateMethodThree();
    }

    //模板内需要实现的方法一
    public abstract void templateMethodOne();

    //模板内需要实现的方法二
    public abstract void templateMethodTwo();

    //模板内需要实现的方法三
    public abstract void templateMethodThree();
}
