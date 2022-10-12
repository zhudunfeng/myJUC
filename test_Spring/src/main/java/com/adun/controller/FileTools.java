package com.adun.controller;

import java.io.File;

/**
 * @author ADun
 * @date 2022/8/21 23:01
 */
public class FileTools {
    public static void delAllFiles(String realPath) {
        File file = new File(realPath);
        if (file.isDirectory()) {
            delAllFiles(file.getAbsolutePath());
        }else {
            File[] files = file.listFiles();
            for (File file1 : files) {
                file1.deleteOnExit();
            }
        }
    }
}
