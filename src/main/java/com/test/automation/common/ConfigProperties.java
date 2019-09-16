package com.test.automation.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {
	
	private Properties configProperties;
	
	public ConfigProperties() throws IOException{
			if(!(configProperties instanceof Properties)) {
				FileInputStream configFile = new FileInputStream(AppConstants.PROPERTY_FILE_PATH.getValue().toString() + File.separator + AppConstants.PROPERTY_FILE_NAME.getValue().toString());
				configProperties = new Properties();
				configProperties.load(configFile);
			}		
	}
	
	public String getProperty(String propertyKey) {
		return this.configProperties.getProperty(propertyKey);
	}
	
	public void setProperty(String propertyKey, String propertyValue) {
		this.configProperties.setProperty(propertyKey, propertyValue);
	}
}
