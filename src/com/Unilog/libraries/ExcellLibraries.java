package com.Unilog.libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcellLibraries {
	
	public String getExcelData(String sheetName, int rowNum, int cellNum){
		String val=null;
		try {
			FileInputStream fis = new FileInputStream("Unilog.xlsx.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			//Get a particular sheet in the workbook
			Sheet s = wb.getSheet(sheetName);
			//Get a particular row from that sheet
			Row r = s.getRow(rowNum);
			//Get a particular cell from that row. Zero based index
			Cell c = r.getCell(cellNum);
			//Get the string value from that cell. Zero based index
			val = c.getStringCellValue();		
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return val;
	}

	//Generic method to get the row count from a particular sheet
	//in Excel
	public int getRowCount(String sheetName){
		int rowCount=0;
		try {
			FileInputStream fis = new FileInputStream("Unilog.xlsx.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			//Get a particular sheet in the workbook
			Sheet s = wb.getSheet(sheetName);
			rowCount = s.getLastRowNum();				
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	//Generic function to write data to an empty cell
	public void setExcelData(String sheetName, int rowNum, int cellNum, String data){
		try {
			FileInputStream fis = new FileInputStream("Unilog.xlsx.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			//Get a particular sheet in the workbook
			Sheet s = wb.getSheet(sheetName);
			//Get a particular row from that sheet. If the row is empty
			//use createRow() instead of getRow()
			Row r = s.getRow(rowNum);
			//create a cell in that row.
			Cell c = r.createCell(cellNum);
			//set the string value for that cell.
			c.setCellValue(data);		
			FileOutputStream fos = new FileOutputStream("Unilog.xlsx.xlsx");
			wb.write(fos);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
