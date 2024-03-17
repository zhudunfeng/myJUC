package com.adun.test_time;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @author ADun
 * @date 2023/8/7 14:04
 */
@SpringBootTest
public class TimeTests {

    /**
     *获取当前时间到今日23：59：59的秒数
     */
    @Test
    public void getCurrTimeTo24(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = dateTimeFormatter1.format(now);
        System.out.println(today);
        String today24 = today + " 23:59:59";

        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime day24 = LocalDateTime.parse(today24, dateTimeFormatter2);
        System.out.println(day24);

        long curr = now.toEpochSecond(ZoneOffset.ofHours(8));
        long curr24 = day24.toEpochSecond(ZoneOffset.ofHours(8));
        System.out.printf("curr24-curr %d\n",(curr24-curr));
    }

}
