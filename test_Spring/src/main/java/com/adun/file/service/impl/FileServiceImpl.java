package com.adun.file.service.impl;

import com.adun.config.util.ExcelImportUtil;
import com.adun.file.service.IFileService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.InputStream;

/**
 * @author Zhu Dunfeng
 * @date 2022/2/7 16:15
 */
@Service
public class FileServiceImpl implements IFileService {
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchImport(InputStream inputStream) throws Exception {

        ExcelImportUtil excelImportUtil = new ExcelImportUtil(inputStream);
        //获取工作薄
//        Sheet sheet = excelImportUtil.getSheet();
        Workbook workbook = excelImportUtil.getWorkbook();
        int numberOfSheets = workbook.getNumberOfSheets();
        System.out.println(numberOfSheets);
        Sheet sheet = workbook.getSheetAt(0);
        Sheet sheet1 = workbook.getSheetAt(1);


        //获取每行
        for (Row rowData : sheet) {

            //获取当前行的索引值：标题行
            if (rowData.getRowNum() == 0) {
                continue;//忽略当前行
            }

            //获取数据行
            //一级分类
            Cell levelOneCell = rowData.getCell(0);
            String levelOneCellValue = excelImportUtil.getCellValue(levelOneCell).trim();
            if (levelOneCell == null || StringUtils.isEmpty(levelOneCellValue)) {
                continue;//跳过空行
            }

            //二级分类
            Cell levelTwoCell = rowData.getCell(1);
            String levelTwoCellValue = excelImportUtil.getCellValue(levelTwoCell).trim();
            if (levelTwoCell == null || StringUtils.isEmpty(levelTwoCellValue)) {
                continue;//跳过空行
            }
//
//            //判断一级分类是否重复
//            Subject subject = this.getByTitle(levelOneCellValue);
//            //二级分类的parent_id
//            String parentId = null;
//            if (subject == null) {
//                //将一级分类存入数据库
//                Subject subjectLevelOne = new Subject();
//                subjectLevelOne.setTitle(levelOneCellValue);
//                baseMapper.insert(subjectLevelOne);
//
//                //二级分类的parent_id
//                parentId = subjectLevelOne.getId();
//            } else {
//                //二级分类的parent_id
//                parentId = subject.getId();
//            }
//
//            //判断二级级分类是否重复
//            Subject subByTitle = this.getSubByTitle(levelTwoCellValue, parentId);
//            if (subByTitle == null) {
//                //将二级分类存入数据库
//                Subject subjectLevelTwo = new Subject();
//                subjectLevelTwo.setTitle(levelTwoCellValue);
//                subjectLevelTwo.setParentId(parentId);
//                baseMapper.insert(subjectLevelTwo);
//            }
        }
        return;
    }
}
