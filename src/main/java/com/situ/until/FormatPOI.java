package com.situ.until;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FormatPOI {

    public static Workbook exportExcel(List<?> dataList, List<String> propList, List<String> fieldName)
            throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("list");
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < fieldName.size(); i++) {
            row.createCell(i).setCellValue(fieldName.get(i));
        }
        if(FmtEmpty.isEmpty(dataList)||FmtEmpty.isEmpty(propList)) {
        	 return workbook;
        }
        Class<? extends Object> c = dataList.get(0).getClass();
        for (int i = 0; i < dataList.size(); i++) {
            Object obj = dataList.get(i);
            row = sheet.createRow(i + 1);
            for (int k = 0; k < propList.size(); k++) {
                Field f = c.getDeclaredField(propList.get(k));
                f.setAccessible(true);
                row.createCell(k).setCellValue(f.get(obj).toString());
            }
        }
        return workbook;
    }

}
