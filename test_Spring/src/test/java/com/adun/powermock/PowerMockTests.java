package com.adun.powermock;

import com.adun.Address;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

/**
 * @author Zhu Dunfeng
 * @date 2022/6/2 14:17
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PowerMockTests.User.class, Address.class})
public class PowerMockTests {


    //    powermock
    @Test
    public void testSize() {
        Integer expected = 100;
        //mock获取对象实例
        List list = PowerMockito.mock(List.class);
        PowerMockito.when(list.size()).thenReturn(expected);
        Integer actual = list.size();
        Assert.assertEquals("返回值不相等", expected, actual);

        User user = new User();
        user.setName("zhangsan");
        user.setAge(1);
        System.out.println("user = " + user);

    }

    @Test
    public void testFinial() {
        String expected = "beijing";
//        User user = PowerMockito.mock(User.class);
//        PowerMockito.when(user.test()).thenReturn(expected);
//        String actual = user.test();
//        Assert.assertEquals("返回值不相等",  expected,  actual);
        Address address = PowerMockito.mock(Address.class);
        PowerMockito.when(address.getAddress()).thenReturn(expected);
        String actual = address.getAddress();
        System.out.println("actual = " + actual);
        Assert.assertEquals("返回值不相等", expected, actual);
    }


    @Test
    public void testGet() {
        int index = 0;
        Integer expected = 100;
        List<Integer> mockList = PowerMockito.mock(List.class);
        PowerMockito.when(mockList.get(index)).thenReturn(expected);
        Integer actual = mockList.get(index);
        Assert.assertEquals("返回值不相等", expected, actual);
    }

     final class User {
        private String name;

        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String  test(){
            return name +":"+age;
        }
        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}
