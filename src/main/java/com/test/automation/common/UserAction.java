package com.test.automation.common;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;


@Component
public class UserAction {
	
	private static final Logger LOG = Logger.getLogger(UserAction.class.getName());
	
	public boolean click(WebElement element, String elementName) {
		boolean isClicked = false;
		
		try {
			element.click();
			isClicked = true;
			LOG.info("Successfully clicked on :" + elementName);
		} catch(Exception exp) {
			LOG.error("Exception while clicking on " + elementName + ". Exception is:" + exp.getStackTrace());			
		}
		
		return isClicked;
	}
	
	public boolean clickByJS(WebDriver driver, WebElement element, String elementName) {
		boolean isClicked = false;
		
		try {
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			isClicked = true;
			LOG.info("Successfully clicked on :" + elementName);
		} catch(Exception exp) {
			LOG.error("Exception while clicking on " + elementName + ". Exception is:" + exp.getStackTrace());			
		}
		
		return isClicked;
	}
	
	public boolean enter(WebElement element, String value, String elementName) {
		boolean isEntered = false;
		
		try {
			element.sendKeys(value);;
			isEntered = true;
			LOG.info("Successfully date entered in :" + elementName);
		} catch(Exception exp) {
			LOG.error("Exception while entering data in " + elementName + ". Exception is:" + exp.getStackTrace());	
		}
		
		return isEntered;
	}
	
	public void launchUrl(WebDriver driver, String url) {
		LOG.info("Browser Launching with URL " + url);
		driver.manage().timeouts().implicitlyWait((Integer)AppConstants.BROWSER_IMPLICIT_WAIT.getValue(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public void waitForElementToVisible(WebElement element, long timeout, String elementDescription) {
		long startTime = System.currentTimeMillis();
		while((System.currentTimeMillis() - startTime)<= timeout) {
			try {
				if(element.isDisplayed()) {
					break;
				}
			} catch(Exception exp) {
				LOG.error("Error while waiting for element " + elementDescription);
			}			
		}
		
	}
	
	public void mouseHover(WebDriver driver, WebElement element) {
		Actions mouseAction = new Actions(driver);
		mouseAction.moveToElement(element).perform();
	}
}
