package com.adun.file.controller;

import com.adun.config.R;
import com.adun.config.util.ExceptionUtils;
import com.adun.file.service.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author Zhu Dunfeng
 * @date 2022/2/7 15:59
 */
@RequestMapping("file")
@RestController
public class FileController {

    Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private IFileService fileService;


    @PostMapping("import")
    public R batchImport(
            @RequestParam("file") MultipartFile file
    ){
        try {
            InputStream inputStream = file.getInputStream();
            fileService.batchImport(inputStream);
            return R.ok().message("批量导入成功");
        } catch (Exception e) {
            //打印原始异常对象中的错误跟踪栈
            log.error(ExceptionUtils.getMessage(e));
//            throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR);
            return null;
        }
    }

}
