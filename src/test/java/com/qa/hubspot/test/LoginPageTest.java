package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.TimeUtil;

public class LoginPageTest 
{
	// Should be write in TestNG.
	
	// We can write in two ways:
	//BasePage basePage = new BasePage();
	
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	
	
	@BeforeTest
	public void setUp() throws InterruptedException
	{
		basePage = new BasePage();
		prop = basePage.init_Prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest()
	{
		String title = loginPage.getLoginPageTitle();
		System.out.println(" LoginPage Title is : "+title);
		System.out.println(" ");
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE," Hup...! The Title is not found..!");
	}
	
	@Test(priority=2)
	public void verifySignUpLinkTest()
	{
		boolean flag = loginPage.checkSighUpLink();
		System.out.println(flag);
	//	Assert.assertTrue(loginPage.checkSighUpLink(), "Sign-Up link is not present....");
	}
	
	@Test(priority=3)
	public void verifyShowPasswordLinkLinkTest()
	{
	//	boolean flag = loginPage.checkSighUpLink();
	//	System.out.println(flag);
		Assert.assertTrue(loginPage.checkShowPasswordLink());
	}
	
	@Test(priority=4)
	public void verifyForgotPasswordLinkTest()
	{
	//	boolean flag = loginPage.checkSighUpLink();
	//	System.out.println(flag);
		Assert.assertTrue(loginPage.checkForgotPasswordLink());
	}
	
	@Test(priority=5)
	public void verifySSObtnTest()
	{
	//	boolean flag = loginPage.checkSighUpLink();
	//	System.out.println(flag);
		Assert.assertTrue(loginPage.checkSSObtn());
	}
	
	
	@Test(priority=6)
	public void loginTest() throws InterruptedException
	{
		String userName = prop.getProperty("username");
		String  password = prop.getProperty("password");
		loginPage.doLogin(userName, password);
	}
	
		
	
	@AfterTest
	public void tearDown() throws InterruptedException
	{
		TimeUtil.mediumWait();
	//	driver.quit();
	}
	
	
}

