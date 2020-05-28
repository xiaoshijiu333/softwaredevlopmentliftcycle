package com.fei.softwaredevlopmentliftcycle.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;


/**
 * @author xiaoshijiu
 * @description 下载导出Excel
 * @package fei.excel
 * @date 2019/5/4
 */
public class DownloadExcel {
    /**
     * @function: 下载导出Excel
     * @param sheetName 表格Sheet名
     * @param titles 表格标题
     * @param datas 表格数据，String类型的二维数组
     */
    public static HSSFWorkbook downloadExcel(String sheetName, String[] titles, String[][] datas) {
        // 1. 创建一个Excel文件对象
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. 创建单元格，设置样式居中
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        // 3. 在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);

        // 4. 在sheet中添加表头第0行，就是标题行
        HSSFRow row = sheet.createRow(0);

        // 5. 声明列对象，并创建标题
        HSSFCell cell = null;

        for (int i = 0, length = titles.length; i < length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            //设置样式
            cell.setCellStyle(style);
        }

        // 6.  创建内容
        for (int i = 0, size = datas.length; i < size; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0, length = datas[i].length; j < length; j++) {
                //将内容按顺序赋给对应的列对象
                cell=row.createCell(j);
                cell.setCellValue(datas[i][j]);
                cell.setCellStyle(style);
            }
        }

        return workbook;
    }
}
