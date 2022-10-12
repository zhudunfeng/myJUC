package com.adun;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Zhu Dunfeng
 * @date 2021/7/30 18:25
 */
@SpringBootTest
public class UUIDTest {

    @Test
    public void printUUID(){
//        System.out.println("UUID.randomUUID() = " + UUID.randomUUID());

        String key=""+new Random().nextInt(10000)+ RandomStringUtils.randomAlphanumeric(5);
        System.out.println(key);

        String random = RandomStringUtils.random(10);
        System.out.println(random);

        int i = RandomUtils.nextInt();
        System.out.println(i);

        Random random1 = new Random();
        System.out.println(random1.nextInt(Integer.MAX_VALUE));
    }

    @Test
    public void printUUID_1(){
        System.out.println("UUID.randomUUID() = " + UUID.randomUUID());
        System.out.println("UUID.randomUUID() = " + UUID.randomUUID().toString());

        UUID uuid = UUID.randomUUID();
        String[] split = uuid.toString().split("\\-");
        System.out.println(Arrays.asList(split));

        String collect = Arrays.stream(split).collect(Collectors.joining());
        System.out.println(collect);
    }

    @Test
    public void datetime(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String format = dateTimeFormatter.format(now);
        System.out.println("format = " + format);
        String format1 = now.format(dateTimeFormatter);
        System.out.println("format1 = " + format1);

        LocalDateTime parse = LocalDateTime.parse(format, dateTimeFormatter);
        System.out.println(parse);


    }

}
