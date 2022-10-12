package com.adun;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zhu Dunfeng
 * @date 2022/6/11 10:39
 */
@SpringBootTest
public class CopyObjTests {

    @Test
    public void testDeepCopy() throws IllegalAccessException, InstantiationException {

        Address address = new Address();
        address.setAddress("北京");
        address.setNum(11);
        Address address1 = new Address();
//        BeanUtils.copyProperties(address, address1);
//        deepCopy(address,address1);
        deepCopy(address, address1);
        System.out.println(address1);
    }

    @Test
    public void testDeepCopy2() throws IllegalAccessException, InstantiationException {

        Address address = new Address();
        address.setAddress("北京");
        address.setNum(11);
        List<String> standbyList = new ArrayList<>();
        standbyList.add("aa");
        standbyList.add("bb");
        address.setStandbyList(standbyList);

        Student student = new Student();
        student.setId("1");
        student.setName("aa");
        student.setAge(18);
        student.setAddress(address);
//        Address[] addresses = {address, address};
//        student.setAddresses(addresses);


        Student student1 = new Student();
//        BeanUtils.copyProperties(address, address1);
        deepCopy(student, student1, true);
        System.out.println(student1);
//        student1.setAddress(new Address("上海", 22));
        student1.getAddress().setAddress("上海");
        student1.getAddress().setNum(22);
        student1.getAddress().getStandbyList().add("dd");
        System.out.println(student);
        System.out.println(student1);
    }

    @Test
    public void testDeepCopy1() throws IllegalAccessException {
//        BeanUtils.copyProperties();
        Address address = new Address();
        address.setAddress("北京");
        address.setNum(11);
        Class<? extends Address> addressClass = address.getClass();
        Field[] declaredFields = addressClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // System.out.println(declaredField);
            Class<? extends Field> aClass = declaredField.getClass();

            //类型
            Class<?> type = declaredField.getType();
            System.out.println(type);
            System.out.println(type.getName());
            System.out.println(type.getSimpleName());
            System.out.println(type.getComponentType());
            System.out.println(type.getProtectionDomain());

            //权限修饰符
            int modifiers = declaredField.getModifiers();
            String s = Modifier.toString(modifiers);
            System.out.println(s);

            //获取obj对象的f属性值
            declaredField.setAccessible(true);
            Object o = declaredField.get(address);
            System.out.println(o);


            //获取声明类
            Class<?> declaringClass = declaredField.getDeclaringClass();
            System.out.println(declaringClass);

            Type genericType = declaredField.getGenericType();
            System.out.println(genericType);

        }
        //获取方法
        Method[] declaredMethods = addressClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
            declaredMethod.setAccessible(true);
//            declaredMethod.invoke()
        }
    }

    public void deepCopy(Object source, Object target) throws IllegalAccessException, InstantiationException {
        deepCopy(source, target, false);
    }

    public void deepCopy(Object source, Object target, boolean isDeep) throws IllegalAccessException, InstantiationException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        String[] types = new String[]{"byte", "short", "int", "long", "float", "double", "boolean", "char",
                "Byte", "Short", "Integer", "Long", "Float", "Double", "Boolean", "Character", "String"};
        Field[] sourceClassDeclaredFields = sourceClass.getDeclaredFields();
        Field[] targetClassDeclaredFields = targetClass.getDeclaredFields();
        for (Field sourceClassDeclaredField : sourceClassDeclaredFields) {
            for (Field targetClassDeclaredField : targetClassDeclaredFields) {
                int modifiers = sourceClassDeclaredField.getModifiers();
                int modifiers1 = targetClassDeclaredField.getModifiers();
                if (Modifier.isFinal(modifiers) || Modifier.isFinal(modifiers1)) {
                    continue;
                }
                if (
                        sourceClassDeclaredField.getName().equals(targetClassDeclaredField.getName())
                                && sourceClassDeclaredField.getType() == targetClassDeclaredField.getType()
                ) {
                    if (!isDeep) {
                        // 判断我们的属性值属于那种数据类型
                        // 1. 获取属性值
                        sourceClassDeclaredField.setAccessible(true);
                        Object o = sourceClassDeclaredField.get(source);
                        targetClassDeclaredField.setAccessible(true);
                        targetClassDeclaredField.set(target, o);
                    } else if (isDeep) {
                        Class<?> type = sourceClassDeclaredField.getType();
                        if (Arrays.asList(types).contains(type.getSimpleName())) {
                            deepCopy(source, target, false);
                        } else {
                            sourceClassDeclaredField.setAccessible(true);
                            targetClassDeclaredField.setAccessible(true);
                            Object o = sourceClassDeclaredField.get(source);
                            Object instance = null;
                            if (type.isInterface()) {
                                instance = o.getClass().newInstance();
                                deepCopy(o, instance,false);
                                targetClassDeclaredField.set(target, instance);
                                continue;
                            } else {
                                instance = type.newInstance();
                            }
                            deepCopy(o, instance, true);
                            targetClassDeclaredField.set(target, instance);
                        }
                    }

                }
            }


            // 2. 判断这个值是否是数组

            // 3. 判断这个值是否是对象

            // 4. 属于简单数据类型
        }
    }


    @Test
    public void test() {
    }
}
