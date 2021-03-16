package com.adun.test_oom;

/**
 * @author zhudunfeng
 * @date 2021/3/10 10:24
 *
 * Java.lang.OutOfMemeoryError:Metaspace
 *
 * 需要使用cglib
 * 去oom项目中查看
 */
/**
 * @author zhudunfeng
 * @date 2020/9/14 23:04
 * 元空间OOM
 *java.lang.OutOfMemoryError: Metaspace
 *
 * JVM参数;
 *  -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
 *  Java8及之后的版本使用Metaspace来代替永久代
 *
 *  Metaspace是方法区在HotSpot中的实现，它与持久代最大的区别在于：Metaspace并不在JVM虚拟机中，而是使用本地内存
 *  也即在java8中，classe metadata（the virtual machines internal presentation of Java class），被存储在叫
 *  Metaspace的native memory
 *
 *  永久代（java8后被元空间Metaspace取代）存储以下信息：
 *
 *  虚拟机加载的类信息
 *  常量池
 *  静态变量
 *  即时编译后的代码
 *
 *  模拟Metaspace空间溢出，我们不断生成类王元空间灌，类占据的空间总是会超过Metaspace指定的空间大小的
 */
public class MetaspaceMemoryDemo {
}
