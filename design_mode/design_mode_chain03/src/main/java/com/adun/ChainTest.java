package com.adun;

import com.adun.bean.Programmer;
import com.adun.chain.Handler;
import com.adun.handler.InfoHandler;
import com.adun.handler.NameHandler;
import com.adun.handler.ProjectHandler;

/**
 * @author ADun
 * @date 2022/8/16 13:59
 */
public class ChainTest {
    public static void main(String[] args) {
        Programmer programmer = new Programmer();
        programmer.setProject("公众号");
        programmer.setName("哪吒编程");
        programmer.setInfo("扫描文末二维码，关注公众号哪吒编程，定期分享Java干货，还有不定期的送书活动，包邮到你家");

        Handler.Builder builder = new Handler.Builder();
        //链式编程，先后顺序,清晰明了
        builder.addHandler(new ProjectHandler()) // 校验项目名称
                .addHandler(new NameHandler())  // 校验名字
                .addHandler(new InfoHandler()); // 校验活动细节

        boolean flag = builder.build().doHandler(programmer);
        System.out.println(flag);
    }
}
