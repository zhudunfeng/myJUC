package com.adun.test_filecheck;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * @author ADun
 * @date 2022/8/21 20:09
 */
@SpringBootTest
public class FileCheck {



    @Test
    public void testFile() throws IOException {
        String path = "C:/Users/21015/Desktop/python-3.10.6-amd64.exe";

        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        String md5Hex = DigestUtils.md5Hex(fileInputStream);
        System.out.println(md5Hex);

    }


    @Test
    public void testFileSec() throws IOException {
        String path = "C:/Users/21015/Desktop/python-3.10.6-amd64.exe";
        MessageDigest md5Digest = DigestUtils.getMd5Digest();

        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel channel = fileInputStream.getChannel();
        long size = channel.size();

        System.out.println(size);
        int oneTenth = (int) size / 10;
        System.out.println(oneTenth);
        for (int i = 0; i < 10; i++) {
            MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, i * oneTenth, 100);
            md5Digest.update(byteBuffer);
        }

        String s = Hex.encodeHexString(md5Digest.digest());
        System.out.println(s);
    }


    /**
     * 大文件分段验证
     * @throws IOException
     */
    @Test
    public void testFileSec2() throws IOException {
        String path = "C:/Users/21015/Desktop/python-3.10.6-amd64.exe";
        MessageDigest md5Digest = DigestUtils.getMd5Digest();

        RandomAccessFile file = new RandomAccessFile(path, "r");

        FileChannel channel = file.getChannel();
        long size = channel.size();
        System.out.println(size);

        int oneTenth = (int) size / 10;
        System.out.println(oneTenth);

        byte[] bytes = new byte[100];
        for (int i = 0; i < 10; i++) {
            file.seek(i*oneTenth);
            int byteCount = file.read(bytes);
            md5Digest.update(bytes, 0, byteCount);
        }

        String s = Hex.encodeHexString(md5Digest.digest());
        System.out.println(s);
    }


    @Test
    public void testDigest() throws Exception {
        String path = "C:/Users/21015/Desktop/python-3.10.6-amd64.exe";
        File file = new File(path);
        String fileMD5String = getFileMD5String(file);
        System.out.println(fileMD5String);
    }


    public String getFileMD5String(File file) throws Exception {
        MessageDigest digester = MessageDigest.getInstance("MD5");

        FileInputStream in = new FileInputStream(file);

        FileChannel ch = in.getChannel();

        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,

                file.length());

        digester.update(byteBuffer);

        return Hex.encodeHexString(digester.digest());

    }


    @Test
    public void testDigest2() throws Exception {
        String path = "C:/Users/21015/Desktop/python-3.10.6-amd64.exe";
        String fileMD5String = getFileMD5String2(path);
        System.out.println(fileMD5String);
    }

    public String getFileMD5String2(String fileName) throws Exception {
        File file = new File(fileName);

        MessageDigest digester = MessageDigest.getInstance("MD5");

        FileInputStream in = new FileInputStream(file);

        byte[] bytes = new byte[256 * 1024];

        int byteCount;

        while ((byteCount = in.read(bytes)) > 0) {

            digester.update(bytes, 0, byteCount);

        }

        return Hex.encodeHexString(digester.digest());

    }


}
