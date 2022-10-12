package com.adun.test_filecheck;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;

/*
 * RandomAccessTile：随机读写访问流
 *     既可以是输入流，也可以是输出流
 *     r|w|d|s:读|写|更新数据|元数据更新
 *
 *     w:是对开头对文件内容进行覆盖
 *
 * */

public class RandomAccessFileTest {

    @Test
    public void test1(){

        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            //1.流对象
            raf1 = new RandomAccessFile(new File("hello.txt"),"r");
            raf2 = new RandomAccessFile(new File("hello2.txt"),"rw");

            //2.读写
            byte[]  buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer))!=-1) {
                raf2.write(buffer,0,len);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        finally {
            try {
                if(raf1!=null)
                    raf1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if(raf2!=null)
                    raf2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * seek随机访问|插入方法
     *
     * */
    @Test
    public void test2() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");
        /*raf1.seek(3);
        raf1.write("abc".getBytes());
    */
        /*
         * 使用StringBuilder
         * */
        StringBuilder sb = new StringBuilder((int)(new File("hello.txt").length()));
        raf1.seek(4L);
        int len;
        byte[] buffer = new byte[20];
        while((len = raf1.read(buffer))!=-1) {
            sb.append(new String(buffer,0,len));
        }

        raf1.seek(4L);
        raf1.write("ADUN".getBytes());
        raf1.write(sb.toString().getBytes());
        raf1.close();
    }

}



