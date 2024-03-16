package com.adun.chain;

import com.adun.bean.Programmer;

/**
 * @author ADun
 * @date 2022/8/16 13:47
 */
public abstract class Handler<T> {

    protected Handler next;

    private void next(Handler next) {
        this.next = next;
    }

    public abstract boolean doHandler(Programmer programmer);

    public static class Builder<T> {
        private Handler<T> head;
        private Handler<T> tail;

        public Builder<T> addHandler(Handler handler) {
            //没有下一节点，将头指针和尾指针指向当前节点
            if (this.head == null) {
                this.head = this.tail = handler;
                return this;
            }
            //有下一节点
            //尾指针链接新节点
            this.tail.next(handler);
            //尾指针走到链表的下一节点
            this.tail = handler;


            //this 表示当前builder对象
            return this;
        }

        public Handler<T> build() {
            return this.head;
        }
    }

}
