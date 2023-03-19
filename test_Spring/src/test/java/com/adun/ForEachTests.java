package com.adun;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * 测试forEach
 * @author ADun
 * @date 2023/2/4 21:03
 */
@SpringBootTest
public class ForEachTests {

    @Test
    public void testForEach(){
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        try {
            list.forEach(v->{
                if(v.equals(5)){
                    //相当于continue
                    return;
                }

                if(v.equals(7)){
                    throw new RuntimeException("这是"+v);
                }
                System.out.println(v);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("后面代码");
    }


}
