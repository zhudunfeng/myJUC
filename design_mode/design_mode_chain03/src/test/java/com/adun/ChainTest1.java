package com.adun;

import com.adun.bean.Programmer;
import com.adun.chain.Handler;
import com.adun.handler.InfoHandler;
import com.adun.handler.NameHandler;
import com.adun.handler.ProjectHandler;
import com.adun.utils.SpringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ADun
 * @date 2022/8/16 13:59
 */
@SpringBootTest
public class ChainTest1 {

//    @Resource
//    private ApplicationContext applicationContext;

    @Test
    public void main() {
        Programmer programmer = new Programmer();
        programmer.setProject("公众号");
        programmer.setName("哪吒编程");
        programmer.setInfo("扫描文末二维码，关注公众号哪吒编程，定期分享Java干货，还有不定期的送书活动，包邮到你家");

        Map<String, Handler> beanMap =
                SpringUtils.getBeansOfType(Handler.class);
        //支持指定链中处理对象的顺序
        List<Handler> beans = beanMap.values().stream().sorted(Comparator.comparing(handler -> {
            Class<? extends Handler> aClass = handler.getClass();
            boolean annotationPresent = aClass.isAnnotationPresent(Order.class);
            if (annotationPresent) {
                Order order = aClass.getAnnotation(Order.class);
                return order.value();
            }
            //默认在最前
            return -1;
        })).collect(Collectors.toList());
        Handler.Builder builder = new Handler.Builder();
        beans.forEach(builder::addHandler);
        boolean flag = builder.build().doHandler(programmer);
        System.out.println(flag);
    }
}
