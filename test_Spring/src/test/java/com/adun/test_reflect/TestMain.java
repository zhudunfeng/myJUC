package com.adun.test_reflect;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * @author ADun
 * @date 2022/8/23 12:34
 */
public class TestMain {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //1.获取运行时类Class
        Class<?> clazz = Class.forName("com.adun.test_reflect.Demo");
        System.out.println("clazz = " + clazz);

        Object obj = clazz.newInstance();

        //2.获取方法
        Method method = clazz.getMethod("test", String.class);
        System.out.println("method = " + method);
        Object zzt = method.invoke(obj, "zzt");

        //3.分析方法的结构
        //权限修饰符 default 0   public 1   protected 2
        int modifiers = method.getModifiers();
        //返回值类型
        Class<?> returnClass = method.getReturnType();
        //方法名
        String methodName = method.getName();
        //参数类型
        Class<?>[] parameterClass = method.getParameterTypes();
        //抛出异常
        Class<?>[] exceptionTypes = method.getExceptionTypes();
        //获取注解
        MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);

        System.out.println("modifiers = " + modifiers);
        System.out.println("methodName = " + methodName);
        System.out.println("parameterClass = " + Arrays.asList(parameterClass));
        System.out.println("exceptionTypes = " + Arrays.asList(exceptionTypes));
        System.out.println("myAnnotation = " + myAnnotation);

        System.out.println("returnClass = " + returnClass);
        //泛型
        Type type = method.getGenericReturnType();
        ParameterizedTypeImpl typeImpl = (ParameterizedTypeImpl) type;
        Type[] types = typeImpl.getActualTypeArguments();
        //泛型类型
        Type patternType = types[0];
        Class realPatternType=(Class)patternType;
        System.out.println("realPatternType = " + realPatternType);
    }
}
