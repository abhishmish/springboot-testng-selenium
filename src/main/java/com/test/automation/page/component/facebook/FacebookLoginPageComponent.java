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
public class FacebookLoginPageComponent{
		
	@Autowired
	private UserAction userAction;
	
	public void setDriver(WebDriver driver) {		
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(how = How.ID, using = "email")
	private WebElement usernameInputBox;
	
	@CacheLookup
	@FindBy(how = How.ID, using = "pass")
	private WebElement passwordInputBox;
	
	@CacheLookup
	@FindBy(how = How.ID, using = "loginbutton")
	private WebElement loginButton;
	
	public void login(String username, String password) {
		userAction.enter(this.usernameInputBox, username, "Facebook Username");
		userAction.enter(this.passwordInputBox, password, "Facebook Password");
		userAction.click(this.loginButton, "Facebook Login Button");
	}

	public WebElement getUsernameInputBox() {
		return usernameInputBox;
	}

	public WebElement getPasswordInputBox() {
		return passwordInputBox;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
}
