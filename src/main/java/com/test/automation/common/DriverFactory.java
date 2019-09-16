package com.test.automation.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverFactory {
	
	private static ConfigProperties configProps;
	
	@Autowired
    public DriverFactory(ConfigProperties configProps){
		DriverFactory.configProps = configProps;
    }
	
	public static <T> WebDriver getDriver(Class<T> driverClass) {		
		WebDriver driver = null;
		if(driverClass.equals(FirefoxDriver.class)) {
			driver = getFirefoxDriver();
		} else{
			driver = getChromeDriver();
		} 
		
		return driver;
	}
	
	private static ChromeDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", configProps.getProperty("browser.chrome.driver.path").trim());
		return new ChromeDriver();
	}
	
	private static FirefoxDriver getFirefoxDriver() {
		System.setProperty("webdriver.firefox.driver", configProps.getProperty("browser.firefox.driver.path"));
		return new FirefoxDriver();
	}
}
