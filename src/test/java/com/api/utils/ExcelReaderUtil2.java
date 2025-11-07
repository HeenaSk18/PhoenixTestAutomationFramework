package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtil2 {
	
	private ExcelReaderUtil2() {
		
	}

	public static<T> Iterator<T> loadTestData(String xlsFileName,String sheetname,Class<T> clazz) {
		// APACHE POI OOXML LIB

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(xlsFileName);//testData/PhoenixTestData.xlsx

		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Focus on the sheet

		XSSFSheet mySheet = myWorkBook.getSheet(sheetname);//LoginTestData
		
		List<T> list=Poiji.fromExcel(mySheet, clazz);
		return list.iterator();
		
	}

}
