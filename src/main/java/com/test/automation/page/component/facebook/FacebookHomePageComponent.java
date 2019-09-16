package com.test.automation.page.component.facebook;
	
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.automation.common.UserAction;

@Component
public class FacebookHomePageComponent{
	
	@Autowired
	private UserAction userAction;
			
	public void setDriver(WebDriver driver) {			
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(how = How.ID, using = "feedx_sprouts_container")	
	private WebElement createPostContentField;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "div[aria-label^='Write something here']")	
	private WebElement postContenetInputBox;
	
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "button[data-testid*=post-button]")	
	private WebElement createPostButton;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "div[data-click='home_icon'] a")
	private WebElement homeLink;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "div[class*='uiLayer']")
	private WebElement homePage;
	
	private WebElement postedMessageField;
	
	public WebElement getHomeLink() {
		return this.homeLink;
	}
	
	public void postMessage(String message) {
		userAction.click(this.createPostContentField, "Post Message Input box");
		userAction.enter(this.postContenetInputBox, message, "Post Message");
		userAction.click(this.createPostButton, "Create Post Button");
	}
	
	public WebElement setPostedMessageField(WebElement postedMessage) {
		this.postedMessageField = postedMessage;
		return this.postedMessageField;
	}
	
	public WebElement getPostedMessage() {
		return this.postedMessageField;
	}
	
	public WebElement getHomePage() {
		return this.homePage;
	}
}
