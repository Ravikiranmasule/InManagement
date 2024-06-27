package com.luxtavern.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.luxtavern.entity.Category;

public class Helper {
	
	private static final Logger logger=LoggerFactory.getLogger(Helper.class);
	public static String [] HEADERS={
		"id",
		"title",
		"decription",
		"coverimage"
	};
     public static String SHEET_NAME="category_data";
     
     public static ByteArrayInputStream datatoExcel(List<Category> list) throws IOException {
    		Workbook workBook=new XSSFWorkbook();
			ByteArrayOutputStream out =new ByteArrayOutputStream();
    	 try {
		
			Sheet sheet = workBook.createSheet(SHEET_NAME);
			Row row=sheet.createRow(0);
			for(int i=0;i<HEADERS.length;i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(HEADERS[i]);
				
			}
			
			int rowIndex=1;
			for(Category c:list) {
				Row row1=sheet.createRow(rowIndex);
				rowIndex++;
				row1.createCell(0).setCellValue(c.getCategoryId());
				row1.createCell(1).setCellValue(c.getCategoryTitle());
				row1.createCell(2).setCellValue(c.getCategoryDescription());
				row1.createCell(3).setCellValue(c.getCategoryImage());
			}
			workBook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			logger.error("failed to import data in exel");
			e.printStackTrace();
			return null;
		}finally {
			workBook.close();
			out.close();
		}
		
     }
    
}
