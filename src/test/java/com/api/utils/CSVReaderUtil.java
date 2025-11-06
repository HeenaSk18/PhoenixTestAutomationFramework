package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

	// Constructor is private
	// static - static methods!
	// Job : Help me Read the CSV file and Map it a Bean

	private CSVReaderUtil() {
		// No one can create Object of CSVReaderUtil Outside the Class
		// Singleton Class Constructors are private

	}

	public static <T> Iterator<T> loadCSV(String pathOFCSVFile,Class<T> bean) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOFCSVFile);
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr); // CSVReader Constructor
		//Class<UserBean> bean = UserBean.class;
		
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();

		List<T> list = csvToBean.parse();
		//System.out.println(userList);
		return list.iterator();
	}

}
