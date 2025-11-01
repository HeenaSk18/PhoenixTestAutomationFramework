package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {
//WAP to read the Properties file from src/test/resources/config.properties

	//public static void main(String[] args) throws IOException {
		//Special Class:  Properties
	
//	public static String getProperty (String key) throws IOException {
//		Properties prop = new Properties(); //Create the object of properties class
//		
		//Load the Properties file using the load()
		
	private static Properties prop = new Properties();
	
	private ConfigManagerOLD() {
		//Private Constructor!!!
	}
	
	
	static {
		//Operation of loading the properties file in the memory!
		//static block it will executed! Once During_____Class loading Time!_____
		
		
		File configFile = new File(
				System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		FileReader fileReader =null;
		// new java.io.FileReader(configFile);
		
		
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch(FileNotFoundException e) {
		//TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getProperty(String key) {
		//System.out.println(prop.getProperty(key));
		return prop.getProperty(key);
	}
}
