package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;
import com.qa.hubspot.utils.TimeUtil;

public class ContactsPageTest 
{
	// Should be write in TestNG.
	
			// We can write in two ways:
			//BasePage basePage = new BasePage();
			
			BasePage basePage;
			Properties prop;
			WebDriver driver;
			LoginPage loginPage;
			HomePage homepage;
			ContactsPage contactspage;
			
			
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
				contactspage = homepage.gotoContactsPage();
			}
	
			@Test(priority=1)
			public void verifyContactsPageTitle()
			{
				String title = contactspage.getContactsPageTitle();
				System.out.println("Contacts Page Title is : "+title);
				Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
			}
			
			
			@DataProvider
			public Object[][] getContactsTestdata()
			{
			   Object data[][] =	ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
			   return data;
			}
			
			
			@Test(priority=2, dataProvider="getContactsTestdata")
			public void createNewConactsTest(String ename, String firstname, String lastname, String jobType) throws InterruptedException
			{
				String name = contactspage.createNewContact(ename,firstname,lastname,jobType);
				System.out.println(name);
				Assert.assertEquals(name,firstname+" "+lastname);
			}
			
			
			@AfterTest
			public void tearDown() throws InterruptedException
			{
				TimeUtil.mediumWait();
			//	driver.quit();
			}
			
			
			
}
