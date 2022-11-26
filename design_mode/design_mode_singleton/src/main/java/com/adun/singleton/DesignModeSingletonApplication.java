package com.adun.singleton;

import com.adun.singleton.enums.EnumSingleton;
import com.adun.singleton.hunger.HungerSingleton;
import com.adun.singleton.innerclass.InnerSingleton;
import com.adun.singleton.lazy.LazySingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesignModeSingletonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignModeSingletonApplication.class, args);

        System.out.println("===================饿汉==================");
        HungerSingleton hungerSingleton1 = HungerSingleton.getHungerSingleton();
        HungerSingleton hungerSingleton2 = HungerSingleton.getHungerSingleton();
        System.out.println(hungerSingleton1);
        System.out.println(hungerSingleton2);

        System.out.println("===================懒汉==================");
        LazySingleton lazySingleton1 = LazySingleton.getLazySingleton();
        LazySingleton lazySingleton2 = LazySingleton.getLazySingleton();
        System.out.println(lazySingleton1);
        System.out.println(lazySingleton2);

        System.out.println("===================内部类==================");
        System.out.println(InnerSingleton.getInstance());
        System.out.println(InnerSingleton.getInstance());

        System.out.println("===================枚举==================");
        System.out.println(EnumSingleton.INSTANCE.hashCode());
        System.out.println(EnumSingleton.INSTANCE.hashCode());
    }

}
