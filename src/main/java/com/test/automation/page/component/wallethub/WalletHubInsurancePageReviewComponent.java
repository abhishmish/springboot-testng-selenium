package com.test.automation.page.component.wallethub;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
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
public class WalletHubInsurancePageReviewComponent{
		
	@Autowired
	private UserAction userAction;
	
	private WebDriver driver;
		
	public void setDriver(WebDriver driver) {	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "a[href$='reviews']")
	private WebElement reviewsLink;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "div[class^='review-action']")
	private WebElement ratingSection;
	
	@CacheLookup
	@FindAll(@FindBy(how = How.CSS, using = "div[class*='review-action'] svg[class='rvs-star-svg']"))
	private List<WebElement> ratingStars;
	
	@FindAll(@FindBy(how = How.CSS, using = "div[class*='review-action'] svg[class='rvs-star-svg'] path[stroke]"))
	private List<WebElement> reviewPageRatedStars;
	
	@FindAll(@FindBy(how = How.CSS, using = "write-review svg[class='rvs-star-svg'] path[stroke][fill='none']"))
	private List<WebElement> commentPageRatedStars;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "div[class*='dropdown second']")
	private WebElement policyDropdown;
	
	@CacheLookup
	@FindBy(how = How.XPATH, using = "//li[@class='dropdown-item' and text()='Health Insurance']")
	private WebElement policyOption;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "textarea[placeholder^='Write your review']")
	private WebElement reviewCommentInputBox;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "div[class^='sbn-action']")
	private WebElement submitReviewButton;
	
	public WebElement getSubmitReviewButton() {
		return this.submitReviewButton;
	}
	
	public WebElement getReviewCommentInputBox() {
		return this.reviewCommentInputBox;
	}
	
	public WebElement setPolicyOption(String policyOption) {
		this.policyOption = this.driver.findElement(By.xpath("//li[@class='dropdown-item' and text()='" + policyOption + "']"));
		return this.policyOption;
	}
	
	public WebElement getPolicyOption() {
		return this.policyOption;
	}
	
	public WebElement getPolicyDropdown() {
		return this.policyDropdown;
	}
	
	public List<WebElement> getRatingStars(){
		return this.ratingStars;
	}
	
	public List<WebElement> getRatedStars(){
		return this.reviewPageRatedStars;
	}
	
	public WebElement getRatingSection() {
		return this.ratingSection;
	}
	
	public WebElement getReviewsLink() {
		return this.reviewsLink;
	}
	
	public void submitReview(String policyOption, String reviewComment) {
		this.userAction.click(this.policyDropdown, "Policy Dropdown");
		this.userAction.click(this.setPolicyOption(policyOption), policyOption + " Policy");
		this.userAction.enter(this.reviewCommentInputBox, reviewComment, "Review Comment Field");
		this.userAction.click(this.submitReviewButton, "Submit Review");
	}
}
