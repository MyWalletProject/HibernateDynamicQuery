package com.example.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.HibernateDynamicQueryApplication;


public class KYCUtilities {

	
	private static final Logger logger = LoggerFactory.getLogger(KYCUtilities.class);

	public static boolean isNull(Object obj){
		return (obj == null)? true : false;
	}

	public static Properties propertiesFileReader(String filePath){

		Properties prop = null;
		InputStream input = null;
		try{
			input = HibernateDynamicQueryApplication.class.getClassLoader().getResourceAsStream(filePath);
			prop = new Properties();
			prop.load(input);
		}catch (Exception e) {
			logger.info("Exception in Properties file Reading : "+e.getMessage());
			e.printStackTrace();
			return null;
		}
		return prop;
	}
}
