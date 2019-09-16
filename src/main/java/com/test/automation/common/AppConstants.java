package com.test.automation.common;

public enum AppConstants {
	CHROME("chrome"), 
	BROWSER_IMPLICIT_WAIT(60), 
	TIME_OUT(30000L),
	PROPERTY_FILE_PATH("src/test/resources"), 
	PROPERTY_FILE_NAME("application.properties");			
	
	private final Object enumKeyValue; 
	private AppConstants(Object enumKeyValue) {
		this.enumKeyValue = enumKeyValue;
	}
	public Object getValue() {
		return this.enumKeyValue;
	}
}
