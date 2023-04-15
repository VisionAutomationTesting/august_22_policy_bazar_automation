package com.policybazar.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

	public static Properties readPropertyFile(String filePath) {
		Properties prop=new Properties();
		
		try {
			FileInputStream file=new FileInputStream(filePath);
			prop.load(file);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return prop;
	}
}
