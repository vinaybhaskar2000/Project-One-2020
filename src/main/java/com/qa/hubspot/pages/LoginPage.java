package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.test.HomePageTest;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class LoginPage extends BasePage
{
	WebDriver driver;
	
	// 1. Locators
	By username = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up 123");
	By shwPwdLink = By.xpath("//span[text()='Show Password']");
	By forgotPwdLink = By.linkText("Forgot my password");
	By ssoBtn = By.id("ssoBtn");

	ElementUtil elementUtil;
	
	//2.  Constructor of the Page Class we have to create.
	
	
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	// 3. Page Actions / Methods.
	
	public String getLoginPageTitle()
	{
		//return driver.getTitle();
		return elementUtil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
		
	}
	
	
	public boolean checkSighUpLink()
	{
	//	boolean sp = driver.findElement(signUpLink).isDisplayed();
	//	return sp;
	return elementUtil.doIsDisplayed(signUpLink);
	}
	
	public boolean checkShowPasswordLink()
	{
		boolean sp = driver.findElement(shwPwdLink).isDisplayed();
		return sp;
	}
	
	public boolean checkForgotPasswordLink()
	{
		boolean fp = driver.findElement(forgotPwdLink).isDisplayed();
		return fp;
	}
	
	public boolean checkSSObtn()
	{
		boolean sso = driver.findElement(ssoBtn).isDisplayed();
		return sso;
	}
	
	public HomePage doLogin(String un, String pwd) throws InterruptedException
	{
	//	driver.findElement(username).sendKeys(un);
	//	driver.findElement(password).sendKeys(pwd);
	//	driver.findElement(loginBtn).click();
			
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		return new HomePage(driver);
		
	/* According to POM, whenever we are clicking on a Button and 
		landing on someother page that method should return Landing Page Class Object. */	
	//	Thread.sleep(5000);		
		
	}
	
	
	
}
