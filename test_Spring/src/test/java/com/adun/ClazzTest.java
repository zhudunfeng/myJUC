package com.adun;

import com.adun.common.utils.A;
import com.adun.entities.Dept;
import com.adun.entities.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhu Dunfeng
 * @date 2021/8/4 10:14
 *
 * 收集一个指定包下的所有类的全类名与实例名称
 */
@SpringBootTest
public class ClazzTest {
    private final String BASE_PACKAGE = "com.adun.common";
    private final String RESOURCE_PATTERN = "/**/*.class";

    @Test
    public void test() {
        Map<String, Class> handlerMap = new HashMap<String, Class>();
        List<String> clazzList = new ArrayList<>();
        List<String> clazzInstancelist = new ArrayList<>();

        //spring工具类，可以获取指定路径下的全部类
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(BASE_PACKAGE) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory readerfactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                System.out.println("resource = " + resource);
                //用于读取类信息
                MetadataReader reader = readerfactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                System.out.println("classname = " + classname);
                Class<?> clazz = Class.forName(classname);
                System.out.println("clazz.getSimpleName() = " + clazz.getSimpleName());
                System.out.println("clazz.getName() = " + clazz.getName());
                System.out.println("clazz.getCanonicalName() = " + clazz.getCanonicalName());
                System.out.println("clazz.getGenericSuperclass() = " + clazz.getGenericSuperclass());
                System.out.println("clazz.getTypeName() = " + clazz.getTypeName());
                System.out.println("clazz.getPackage() = " + clazz.getPackage());
//                System.out.println("clazz.getProtectionDomain() = " + clazz.getProtectionDomain());
                //判断是否有指定主解
//                MyAnno anno = clazz.getAnnotation(MyAnno.class);
//                if (anno != null) {
//                    //将注解中的类型值作为key，对应的类作为 value
//                    handlerMap.put(classname, clazz);
//                }
//                handlerMap.put(classname, clazz);
//
//                System.out.println(handlerMap);
                //收集实例名
                clazzInstancelist.add(containString(toLowerFirstChar(clazz.getSimpleName())));
                //收集全类名
                clazzList.add(clazz.getName());
            }

            System.out.println(clazzInstancelist);
            System.out.println(clazzList);
        } catch (IOException | ClassNotFoundException e) {
        }
    }



    // 高效率
    public String toUpperFirstChar(String string) {
        char[] chars = string.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] -= 32;
            return String.valueOf(chars);
        }
        return string;
    }

    // 高效率
    public String toLowerFirstChar(String string) {
        char[] chars = string.toCharArray();
        if (chars[0] >= 'A' && chars[0] <= 'Z') {
            chars[0] += 32;
            return String.valueOf(chars);
        }
        return string;
    }

    public String containString(String s){
        return "${"+s+"}";
    }


    public static void main(String[] args)  {
        System.out.println("第一种实例化：");
        System.out.println(int.class);
        System.out.println(Integer.TYPE);
        System.out.println("第二种实例化：");
        A test = new A();
        System.out.println(test.getClass().getName());
        System.out.println(test.getClass().getSimpleName());

    }

    @Test
    public void testClazz(){

        String json="{\n" +
                "    \"id\":\"1\",\n" +
                "    \"resultList\":[\n" +
                "        {\n" +
                "            \"id\":\"1\",\n" +
                "            \"name\":\"张三\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\":\"2\",\n" +
                "            \"name\":\"张三\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        Dept<User> userDept = parseResultV2(json, User.class);
        System.out.println(userDept);

        Dept<User> userDept1 = JSON.parseObject(json, buildType(Dept.class, User.class));
        System.out.println(userDept1);
    }


    private static <T> Dept<T> parseResultV2(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, new TypeReference<Dept<T>>(clazz) {
        });
    }

    private static <T> Dept<T> parseListResult(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, buildType(Dept.class, User.class));
    }

    private static Type buildType(Type... types) {
        ParameterizedTypeImpl beforeType = null;
        if (types != null && types.length > 0) {
            for (int i = types.length - 1; i > 0; i--) {
                beforeType = new ParameterizedTypeImpl(new Type[]{beforeType == null ? types[i] : beforeType}, null, types[i - 1]);
            }
        }
        return beforeType;
    }

}
