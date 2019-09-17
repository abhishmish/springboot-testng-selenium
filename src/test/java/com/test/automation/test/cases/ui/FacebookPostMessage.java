package com.test.automation.test.cases.ui;

import javax.annotation.Nonnull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.test.automation.common.*;
import com.test.automation.page.component.facebook.*;
import com.test.automation.test.util.AbstractBaseTest;

public class FacebookPostMessage extends AbstractBaseTest {
     
	@Autowired @Nonnull
	private WebDriver driver;
	
	@Autowired @Nonnull
	private FacebookLoginPageComponent fbLoginPage;
	
	@Autowired @Nonnull
	private FacebookHomePageComponent fbHomePage;
	
	@Autowired @Nonnull
	private ConfigProperties configProps;
	
	@Autowired @Nonnull
	private UserAction userAction;	
	
	private long waitTime;
	
	@BeforeMethod
	public void testSetup() {
		fbLoginPage.setDriver(this.driver);
		fbHomePage.setDriver(this.driver);
		waitTime = (long)AppConstants.TIME_OUT.getValue();
	}
    
	@Test
    public void verifyFacebookPostedMessage() {
						
		userAction.launchUrl(driver, configProps.getProperty("facebook.application.url").trim());
		
    	fbLoginPage.login(configProps.getProperty("facebook.username"), configProps.getProperty("facebook.password"));

    	userAction.waitForElementToVisible(fbHomePage.getHomeLink(), waitTime, "Home Link");
    	
    	Assert.assertEquals(fbHomePage.getHomeLink().isDisplayed(), true);
    	
    	userAction.click(fbHomePage.getHomePage(), "Home Page black screen");
    			
    	String message = "Helllo World";
    	
    	fbHomePage.postMessage(message);
    	
    	//Please read this xpath and you will find that it is locating the message with latest time and text
    	WebElement postedMessage = this.driver.findElement(By.xpath("//span[@class='timestampContent' and (text()='Just now' or text()='1 min')]/ancestor::div[contains(@class, 'userContent')]/descendant::div[@data-testid='post_message']/descendant::p[text()='" + message + "']"));    	
    	
    	userAction.waitForElementToVisible(postedMessage, waitTime, "Posted Message");
    	
    	Assert.assertEquals(fbHomePage.setPostedMessageField(postedMessage).isDisplayed(), true);     	    	
    }
	
	@AfterMethod
	public void finishTest() {
		//this.driver.quit();
	}
}
