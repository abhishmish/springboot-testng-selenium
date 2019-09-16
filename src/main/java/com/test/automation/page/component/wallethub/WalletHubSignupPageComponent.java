package com.test.automation.page.component.wallethub;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.test.automation.common.UserAction;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WalletHubSignupPageComponent{
		
	@Autowired
	private UserAction userAction;
	
	private WebDriver driver;
		
	public void setDriver(WebDriver driver) {	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(how = How.NAME, using = "em")
	private WebElement emailAddressInputBox;
	
	@CacheLookup
	@FindBy(how = How.NAME, using = "pw1")
	private WebElement passwordInputBox;
	
	@CacheLookup
	@FindBy(how = How.NAME, using = "pw2")
	private WebElement confirmPasswordInputBox;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "label[class='check'] i.track.bf-icon-ok")
	private WebElement creditScoreAndReportSubscriptionCheckboxIcon;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "label[class='check'] input[type='checkbox']")
	private WebElement creditScoreAndReportSubscriptionCheckbox;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "button[class*='touch-element-cl']")
	private WebElement signupButton;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "h2[ng-if='simple']")
	private WebElement signupSuccessMessageHeader;
	
	public WebElement getJoinButton() {
		return this.signupButton;
	}
	
	public WebElement getSignupSuccessMessageHeader() {
		return this.signupSuccessMessageHeader;
	}
	
	public void signup(String email, String password, boolean isCreditScoreAndReportRequired) {
		userAction.enter(this.emailAddressInputBox, email, "Wallethub Email Id");
		userAction.enter(this.passwordInputBox, password, "Wallethub Password");
		userAction.enter(this.confirmPasswordInputBox, password, "Wallethub Confirm Password");		
		this.subscribeForFreeCreditScoreAndReport(isCreditScoreAndReportRequired);
		userAction.click(this.signupButton, "Wallethub Signup Button");
	}
	
	public void subscribeForFreeCreditScoreAndReport(boolean isRequired) {
		if(isRequired) {
			if(!this.creditScoreAndReportSubscriptionCheckbox.isSelected()) {
				userAction.clickByJS(this.driver, this.creditScoreAndReportSubscriptionCheckbox, "Get My Free Credit Score and Report");
			}
		} else {
			if(this.creditScoreAndReportSubscriptionCheckbox.isSelected()) {
				userAction.clickByJS(this.driver, this.creditScoreAndReportSubscriptionCheckbox, "Get My Free Credit Score and Report");
			}
		}
	}
}
