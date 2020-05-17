package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.TimeUtil;

public class HomePageTest  
{
	// Should be write in TestNG.
	
		// We can write in two ways:
		//BasePage basePage = new BasePage();
		
		BasePage basePage;
		Properties prop;
		WebDriver driver;
		LoginPage loginPage;
		HomePage homepage;
		
		
		@BeforeTest
		public void setUp() throws InterruptedException
		{
			basePage = new BasePage();
			prop = basePage.init_Prop();
			driver = basePage.init_driver(prop);
			loginPage = new LoginPage(driver);
			
			String userName = prop.getProperty("username");
			String  password = prop.getProperty("password");
			homepage = loginPage.doLogin(userName, password);
			
		}
		
		// we got the homePage reference.
		
		@Test(priority=1)
		public void verifyHomePageTitleTest()
		{
			String title = homepage.getHomePageTitle();
			System.out.println(" Home Page Title is : "+title);
			Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
		}
		
		@Test(priority=2)
		public void verifyHomePageHeaderTest() throws InterruptedException	
		{
			String header = homepage.getHomePageHeader();
			System.out.println("Home Page Header is : "+header);
		//	Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
		}
		
		@Test(enabled=false)
		public void verifyLoggedInUserTest()
		{
			String accountName = homepage.getAccountName();
			System.out.println(" Logged in Account Name is : "+ accountName);
			Assert.assertEquals(accountName,prop.getProperty("accountname"));
		}
		
		
		@Test(priority=4)
		public void loggedinImage()
		{
			boolean flag = homepage.acctMenubtn();
	     	System.out.println(flag);
		//	Assert.assertTrue(homepage.acctMenubtn());
		}
		
		
		@AfterTest
		public void tearDown() throws InterruptedException
		{
			TimeUtil.mediumWait();
		//	driver.quit();
		}
		
		
}
