package com.adun.chain1;

import com.adun.dto.ProductDto;

/**
 * @author Zhu Dunfeng
 * @date 2025/3/19 22:01
 */
public abstract class IHandler {

    public IHandler nextHandler;

    public void setNextHandler(IHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public boolean hasNext() {
        return this.nextHandler != null;
    }

    /**
     * 处理责任链逻辑，执行下个环节
     */
    public Boolean handle(ProductDto t) {
        if (hasNext()) {
            return nextHandler.handle(t);
        }
        return null;
    }
}
