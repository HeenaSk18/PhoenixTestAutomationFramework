package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {
		//Code to read the CSV file in Java!!! [Important Interview Question]
		
		
		
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
		
//		File csvFile = new File("C:\\Users\\Heena\\eclipse-workspace\\PhonexTestAutomationFramework\\src\\main\\resources\\testData\\LoginCreds.csv");
//		FileReader fr = new FileReader(csvFile);
		InputStreamReader isr = new InputStreamReader(is);
		
		CSVReader csvReader = new CSVReader(isr); //CSVReader Constructor
		//Requires a Reader 
		
		List<String[]> dataList= csvReader.readAll();
		
			for(String[] dataArray:dataList) {
				System.out.println(dataArray[0]);
				System.out.println(dataArray[1]);
			}
		
	}

}
