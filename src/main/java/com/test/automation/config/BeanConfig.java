package com.test.automation.config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import com.test.automation.common.AppConstants;
import com.test.automation.common.ConfigProperties;
import com.test.automation.common.DriverFactory;

@Configuration
@ComponentScan(basePackages = { "com.test.automation" })
public class BeanConfig{
	
	@Autowired
	private ConfigProperties configProps;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcePlaceHolder() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@DependsOn(value = {"configProperties"})
	public WebDriver getDriver() {	
		return this.getBrowserDriver();
	}
	
	private WebDriver getBrowserDriver() {		
		WebDriver driver = null;
		try {
			if(configProps.getProperty("browser.name").trim().equalsIgnoreCase(AppConstants.CHROME.getValue().toString())) {
				driver = DriverFactory.getDriver(ChromeDriver.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return driver;
	}
}