package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.TimeUtil;

public class HomePage extends BasePage 
{
	WebDriver driver;
	ElementUtil elementUtil;
	
	By header = By.cssSelector("h1.private-page__title");
	By accountMenu = By.id("account-menu");
	By accountName = By.cssSelector("span.account-name");
	By contactsLinkPrimary = By.id("nav-primary-contacts-branch");
	By contactsLinkSecondary = By.id("nav-secondary-contacts");
	
	
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle()
	{
		return elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);
		
		//return driver.getTitle();
	}
	
	public boolean acctMenubtn()
	{
		boolean actBtn = driver.findElement(accountMenu).isDisplayed();
		return actBtn;
	}
	
	
	public String getHomePageHeader() throws InterruptedException
	{
		TimeUtil.mediumWait();
		boolean abc = elementUtil.doIsDisplayed(header);
		if (abc) 
		{
			return elementUtil.doGetText(header);
		}
		return null; 
		
	/*	boolean abc =  driver.findElement(header).isDisplayed();
		if (abc) 
		{
			return driver.findElement(header).getText();
		}
		return null;  */
	}

	public String getAccountName()
	{
		elementUtil.waitForElmentToBePresent(accountName, 10);
		if (elementUtil.doIsDisplayed(accountName)) 
		{
			elementUtil.doGetText(accountName);
		}
		return null;
		
		// The main intention is remove all driver methods here
		/*	boolean g = driver.findElement(accountName).isDisplayed();
		{
			if (g) 
			{
			  return driver.findElement(accountName).getText();	
			}
		}
		return null; */
	}
	
	// Click on Contacts link.
	
	
	public ContactsPage gotoContactsPage()
	{
		clickOnContacts();
		return new ContactsPage(driver);
		
	}
	
	
	private void clickOnContacts()
	{
		elementUtil.waitForElmentToBePresent(contactsLinkPrimary, 10);
		elementUtil.doClick(contactsLinkPrimary);
		elementUtil.waitForElmentToBePresent(contactsLinkSecondary, 5);
		elementUtil.doClick(contactsLinkSecondary);
	}
	
	
	
	
	
	
}
