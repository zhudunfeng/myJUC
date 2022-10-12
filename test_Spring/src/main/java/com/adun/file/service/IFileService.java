package com.adun.file.service;

import java.io.InputStream;

/**
 * @author Zhu Dunfeng
 * @date 2022/2/7
 */
public interface IFileService {
    void batchImport(InputStream inputStream) throws Exception;
}
