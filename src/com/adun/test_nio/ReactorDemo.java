package com.adun.test_nio;

/**
 * @author zhudunfeng
 * @date 2020/11/15 23:16
 *
 * https://mp.weixin.qq.com/s/vWbbn1qXRFVva8Y9yET18Q?spm=a2c6h.12873639.0.0.6dd14a61zrtX6t
 *
 * Reactor：负责响应事件，将事件分发到绑定了对应事件的Handler，如果是连接事件，则分发到Acceptor。
 * Handler：事件处理器。负责执行对应事件对应的业务逻辑。
 * Acceptor：绑定了 connect 事件，当客户端发起connect请求时，Reactor会将accept事件分发给Acceptor处理。
 */
public class ReactorDemo {
}
