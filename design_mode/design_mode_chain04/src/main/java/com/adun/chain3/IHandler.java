package com.adun.chain3;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:01
 */
public abstract class IHandler<T,R> {

    public IHandler<T,R> nextHandler;

    public void setNextHandler(IHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public boolean hasNext() {
        return this.nextHandler != null;
    }

    /**
     * 处理责任链逻辑，执行下个环节
     */
    public R handle(T t){
        if(hasNext()){
            return nextHandler.handle(t);
        }
        return null;
    }
}
