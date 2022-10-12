package com.adun.file;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.zip.ZipInputStream;

/**
 * @author Zhu Dunfeng
 * @date 2022/2/8 14:03
 */
@SpringBootTest
public class ZipStreamTest {
    Logger logger = LoggerFactory.getLogger(ZipInputStream.class);

    @Test
    public void testSubString(){
        String s="aa.txt.doc";
        System.out.println(s.substring(s.lastIndexOf(".")+1));
    }

}
